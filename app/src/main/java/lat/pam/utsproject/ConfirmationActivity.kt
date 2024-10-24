package lat.pam.utsproject

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        // Retrieve data from Intent
        val foodName = intent.getStringExtra("FOOD_NAME")
        val servings = intent.getStringExtra("SERVINGS")
        val name = intent.getStringExtra("NAME")
        val notes = intent.getStringExtra("NOTES")
        val imageResourceId = intent.getIntExtra("IMAGE_RESOURCE_ID", 0)

        // Set confirmation details
        findViewById<TextView>(R.id.tvFoodName).text = "Food Name: $foodName"
        findViewById<TextView>(R.id.tvServings).text = "Number of Servings: $servings pax"
        findViewById<TextView>(R.id.tvName).text = "Ordering Name: $name"
        findViewById<TextView>(R.id.tvNotes).text = "Additional Notes: $notes"
        findViewById<ImageView>(R.id.ivFoodImage).setImageResource(imageResourceId)
    }
}