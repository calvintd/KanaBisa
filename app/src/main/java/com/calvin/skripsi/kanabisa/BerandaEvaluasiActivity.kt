package com.calvin.skripsi.kanabisa

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class BerandaEvaluasiActivity: AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda_evaluasi)

        this.title = "Evaluasi"
        val dbh = DBHelper(this)
        val maxEvalId = dbh.maxEvalId()

        mDrawerLayout = findViewById(R.id.drawer_layout)
        var btnEval: Button = findViewById(R.id.buttonEvaluasi)
        var btnHistori: Button = findViewById(R.id.buttonHistori)
        var txtBerandaEvaluasi: TextView = findViewById(R.id.textBerandaEvaluasi)

        txtBerandaEvaluasi.justificationMode = JUSTIFICATION_MODE_INTER_WORD

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.optionBeranda -> startActivity(Intent(this@BerandaEvaluasiActivity, BerandaActivity::class.java))
                R.id.optionDaftar -> startActivity(Intent(this@BerandaEvaluasiActivity,BunyiActivity::class.java))
                R.id.optionPengantar -> startActivity(Intent(this@BerandaEvaluasiActivity, PengantarActivity::class.java))
                R.id.optionHiragana -> startActivity(Intent(this@BerandaEvaluasiActivity, HiraganaActivity::class.java))
                R.id.optionKatakana -> startActivity(Intent(this@BerandaEvaluasiActivity, KatakanaActivity::class.java))
                R.id.optionKompetensi -> startActivity(Intent(this@BerandaEvaluasiActivity, KompetensiActivity::class.java))
            }

            mDrawerLayout.closeDrawers()
            this.finish()

            true
        }

        btnEval.setOnClickListener {
            val intent = Intent(this@BerandaEvaluasiActivity, EvaluasiActivity::class.java)
            val bundle = Bundle()
            bundle.putBoolean("inProg", false)
            bundle.putInt("id",maxEvalId)
            bundle.putInt("banyak",0)
            bundle.putInt("benar",0)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        btnHistori.setOnClickListener{
            if (dbh.cekJumlahEntriEval() == 0) {
                Toast.makeText(this, "Anda belum pernah melakukan evaluasi!", Toast.LENGTH_SHORT).show()
            }
            else {
                startActivity((Intent(this@BerandaEvaluasiActivity,HistoriActivity::class.java)))
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                mDrawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}