package com.example.letsconnect

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.grpc.InternalChannelz.id

class MainActivity2 : AppCompatActivity(), RVAdapter.FriendsItemDeleteClickInterface,RVAdapter.friendItemClickInterface {

    lateinit var itemsRV: RecyclerView
    lateinit var addFB: FloatingActionButton

    lateinit var list: List<FriendsItems>
    lateinit var vadapter:RVAdapter
    lateinit var vaccineViewModel: FriendsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemsRV=findViewById(R.id.recyclerView)
        addFB=findViewById(R.id.floatingActionButton)
        list=ArrayList<FriendsItems>()
        vadapter= RVAdapter(list,this,this)
        itemsRV.layoutManager= LinearLayoutManager(this)
        itemsRV.adapter=vadapter

        val vaccineRepository=FriendsRepository(FriendsDatabase.getDatabase(this))
        val factory=FriendsViewModelFactory(vaccineRepository)

        vaccineViewModel= ViewModelProvider(this,factory).get(FriendsViewModel::class.java)

        vaccineViewModel.getAllFriendsItems().observe(this, Observer {
            vadapter.list=it
            vadapter.notifyDataSetChanged()
        })


        addFB.setOnClickListener{
            openDialog()

        }


    }

    fun openDialog(){
        val dialog= Dialog(this)
        dialog.setContentView(R.layout.add_dialog_bix)
        val cancelBtn: Button = dialog.findViewById(R.id.cancel)
        val saveBtn: Button =dialog.findViewById(R.id.button)
        val prsnNam: EditText =dialog.findViewById(R.id.prsnNam)
        val vaccineName: EditText =dialog.findViewById(R.id.vaccineName)
        val dose: EditText =dialog.findViewById(R.id.dose)
        val aadhNum: EditText =dialog.findViewById(R.id.aadhNum)
        saveBtn.text="Add"




        saveBtn.setOnClickListener{
            val PersonName: String=prsnNam.text.toString()
            val VaccineName: String=vaccineName.text.toString()
            val Dose: String=dose.text.toString()
            val AadharNum:String=aadhNum.text.toString()
            val DoSe:Int=Dose.toInt()

            if (PersonName.isNotEmpty() && VaccineName.isNotEmpty() && Dose.isNotEmpty() && AadharNum.isNotEmpty()) {
                val items = FriendsItems(PersonName, VaccineName, DoSe, AadharNum)
                vaccineViewModel.insert(items)
                Toast.makeText(applicationContext, "Record Inserted", Toast.LENGTH_SHORT).show()
                vadapter.notifyDataSetChanged()
                dialog.dismiss()
            } else {

                Toast.makeText(applicationContext, "Please enter all data", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        cancelBtn.setOnClickListener{
            dialog.dismiss()
        }
        dialog.show()

    }

    override fun onItemClickDelete(vaccineItems: FriendsItems) {
        vaccineViewModel.delete(vaccineItems)
        vadapter.notifyDataSetChanged()
        Toast.makeText(applicationContext,"Record deleted", Toast.LENGTH_SHORT).show()
    }



    override fun OnFriendItemClick(friendItems: FriendsItems) {
        Toast.makeText(this,"entered", Toast.LENGTH_SHORT).show()


        val dialog= Dialog(this)
        dialog.setContentView(R.layout.edit_dialog_bix)

        val updateBtn: Button =dialog.findViewById(R.id.button)
        val cancelBtn: Button = dialog.findViewById(R.id.cancel)
        val prsnNam: EditText =dialog.findViewById(R.id.prsnNam)
        val vaccineName: EditText =dialog.findViewById(R.id.vaccineName)
        val dose: EditText =dialog.findViewById(R.id.dose)
        val aadhNum: EditText =dialog.findViewById(R.id.aadhNum)


        val personname= friendItems.friendName
        val vaccinename=friendItems.mobileNumber
        val DOSE=friendItems.emailId.toString()
        val aadharnumber=friendItems.note
        val ID=friendItems.id



        prsnNam.setText(personname)
        vaccineName.setText(vaccinename)
        dose.setText(DOSE)

        aadhNum.setText(aadharnumber)


        updateBtn.setOnClickListener{
            val PersonName: String=prsnNam.text.toString()
            val VaccineName: String=vaccineName.text.toString()
            val Dose: String=dose.text.toString()
            val AadharNum:String=aadhNum.text.toString()
            val DoSe:Int=Dose.toInt()

            if (PersonName.isNotEmpty() && VaccineName.isNotEmpty() && Dose.isNotEmpty() && AadharNum.isNotEmpty()) {
                val updatedItems = FriendsItems(PersonName, VaccineName, DoSe, AadharNum)
                updatedItems.id=ID
                vaccineViewModel.update(updatedItems)
                Toast.makeText(applicationContext, "Record Updated", Toast.LENGTH_SHORT).show()
                vadapter.notifyDataSetChanged()
                dialog.dismiss()
            } else {

                Toast.makeText(applicationContext, "Please enter all data", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        cancelBtn.setOnClickListener{
            dialog.dismiss()
        }
        dialog.show()

    }


}