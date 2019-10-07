package id.deuboe.tugas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import id.deuboe.tugas.form.kuasa.FormKuasaActivity
import id.deuboe.tugas.form.mandiri.FormMandiriActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseFirestore.getInstance()

        // declaration
        val noAntrean = findViewById<TextView>(R.id.noAntrean)
        val name = findViewById<TextView>(R.id.name)
        val isMandiri = findViewById<TextView>(R.id.isMandiri)

        val dbCollection = db.collection("antrean").document("edCgs3zRfW5kf7UGE90t")
        dbCollection.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("exist", "${document.id} => ${document.data}")

                    noAntrean.text = document.getString("noAntrean")
                    name.text = document.getString("name")
                    isMandiri.text = document.get("isMandiri").toString()
                } else {
                    Log.d("noexist", "no Document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("error db", "Error getting documents.", exception)
            }

    }

    fun mandiri(view: View) {
        val intent =
            Intent(this, FormMandiriActivity::class.java)
        startActivity(intent)
    }

    fun kuasa(view: View) {
        val intent =
            Intent(this, FormKuasaActivity::class.java)
        startActivity(intent)
    }
}