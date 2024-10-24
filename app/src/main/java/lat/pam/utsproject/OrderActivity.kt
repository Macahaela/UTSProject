package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderActivity : AppCompatActivity() {

    private lateinit var spinnerFood: Spinner
    private lateinit var selectedFoodImage: ImageView
    private lateinit var selectedFoodDescription: TextView
    private lateinit var etServings: EditText
    private lateinit var etName: EditText
    private lateinit var etNotes: EditText
    private lateinit var btnOrder: Button

    // Food list
    private val foodList = listOf(
        // Drinks
        Food("Cappuccino", "Rich espresso topped with steamed milk and froth.", R.drawable.cappuchino),
        Food("Kopi Hitam", "A strong, black coffee brewed without milk.", R.drawable.kopi_hitam),
        Food("Sparkling Tea", "Refreshing tea with a fizzy twist.", R.drawable.sparkling_tea),

        // Foods
        Food("Batagor", "Delicious fried fish dumplings.", R.drawable.batagor),
        Food("Black Salad", "A fresh salad with leafy greens, olives, and a tangy dressing.", R.drawable.black_salad),
        Food("Cheesecake", "Creamy dessert with graham cracker crust.", R.drawable.cheesecake),
        Food("Cireng", "Indonesian snack made from fried tapioca flour.", R.drawable.cireng),
        Food("Donut", "Sweet fried dough pastry, often glazed or filled.", R.drawable.donut),
        Food("Mie Goreng", "Stir-fried noodles with vegetables and protein.", R.drawable.mie_goreng),
        Food("Nasi Goreng", "Indonesian fried rice with eggs, vegetables, and seasoning.", R.drawable.nasigoreng)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        // Setup layout insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        spinnerFood = findViewById(R.id.spinnerFood)
        selectedFoodImage = findViewById(R.id.selectedFoodImage)
        selectedFoodDescription = findViewById(R.id.selectedFoodDescription)
        etServings = findViewById(R.id.etServings)
        etName = findViewById(R.id.etName)
        etNotes = findViewById(R.id.etNotes)
        btnOrder = findViewById(R.id.btnOrder)

        // Setup spinner adapter
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, foodList.map { it.name })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFood.adapter = adapter

        // Set spinner item selection listener
        spinnerFood.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedFood = foodList[position]
                selectedFoodImage.setImageResource(selectedFood.imageResourceId)
                selectedFoodDescription.text = selectedFood.description
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedFoodImage.setImageResource(0)
                selectedFoodDescription.text = ""
            }
        }

        // Setup order button click event with input validation
        btnOrder.setOnClickListener {
            val servings = etServings.text.toString().trim()
            val name = etName.text.toString().trim()
            val notes = etNotes.text.toString().trim()

            // Validate input fields
            if (servings.isEmpty() || name.isEmpty()) {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val selectedFood = foodList[spinnerFood.selectedItemPosition]

                // Pass data to ConfirmationActivity
                val intent = Intent(this, ConfirmationActivity::class.java).apply {
                    putExtra("FOOD_NAME", selectedFood.name)
                    putExtra("SERVINGS", servings)
                    putExtra("NAME", name)
                    putExtra("NOTES", notes)
                    putExtra("IMAGE_RESOURCE_ID", selectedFood.imageResourceId)
                }
                startActivity(intent)
            }
        }
    }
}