package com.example.newsappuser

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsappuser.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var ref: DatabaseReference
    private val TAG = "MainActivity"
    lateinit var db: FirebaseFirestore
    var newslist = ArrayList<newslistmodel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ref = FirebaseDatabase.getInstance().reference
        db = FirebaseFirestore.getInstance()
        newslist = ArrayList()
        newslist.clear()

        binding.btn.setOnClickListener {
//            f()
        }

        db.collection("user").addSnapshotListener(object : EventListener<QuerySnapshot> {

            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {

                if (error == null) {
                    val data2: List<newslistmodel> = value!!.toObjects(newslistmodel::class.java)

                    newslist.addAll(data2)


                    var adapter = newslistAdapter()
                    binding.rcv.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.rcv.adapter = adapter
                    adapter.update(newslist)
                    adapter.read(newslist)

                }
            }

        })


    }

    private fun f() {
        ref.root.child("news").addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                newslist.clear()
                for (snap in snapshot.children) {
                    var data = snap.getValue(newslistmodel::class.java)
                    newslist.add(data!!)
                }
//
//                var adapter = newslistAdapter()
//                binding.rcv.layoutManager = LinearLayoutManager(this@MainActivity)
//                binding.rcv.adapter = adapter
//                adapter.update(newslist)
//                Toast.makeText(this@MainActivity, "+++++++++++++++++++++++", Toast.LENGTH_SHORT)
//                    .show()
            }

            override fun onCancelled(error: DatabaseError) {

                Log.e(TAG, "onCancelled: =========== $error")
            }

        })

    }
}