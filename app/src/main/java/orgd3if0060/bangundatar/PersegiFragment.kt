package orgd3if0060.bangundatar


import android.content.ActivityNotFoundException
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.fragment_persegi.*
import orgd3if0060.bangundatar.databinding.FragmentPersegiBinding

/**
 * A simple [Fragment] subclass.
 */
class PersegiFragment : Fragment() {

    private lateinit var binding: FragmentPersegiBinding
    private var luas = 0
    private var kel = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_persegi, container, false)

        if (savedInstanceState != null){
            luas = savedInstanceState.getInt("l")
            kel = savedInstanceState.getInt("k")

            binding.tLuaspp.text = luas.toString()
            binding.tKelpp.text = kel.toString()
        }

        binding.apply {
            hitung1Btn.setOnClickListener {
                val p= e_panjang.text.toString()
                val l = e_lebar.text.toString()

                if (p.isEmpty() || l.isEmpty()){
                    Toast.makeText(context,"Harus Diisi !!",Toast.LENGTH_SHORT).show()
                }else{
                    luas = p.toInt() * l.toInt()
                    t_luaspp.text = luas.toString()

                    kel = 2 * (p.toInt() + l.toInt())
                    t_kelpp.text = kel.toString()
                }
            }
            reset1Btn.setOnClickListener {
                e_panjang.text.clear()
                e_lebar.text.clear()

                t_kelpp.text = null
                t_luaspp.text = null
            }

            share1Btn.setOnClickListener {
                if (t_kelpp.text.toString().isEmpty() || t_luaspp.text.toString().isEmpty()){
                    Toast.makeText(context,"Belum Ada Hasil !!",Toast.LENGTH_SHORT).show()
                }else {
                    val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
                        .setText("Luas Persegi Panjang      = $luas\n" +
                                "Keliling Persegi Panjang  = $kel")
                        .setType("text/plain")
                        .intent
                    try {
                        startActivity(shareIntent)
                    }catch (e: ActivityNotFoundException){
                        Toast.makeText(context,"Tidak Ditemukan !!",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("l", luas)
        outState.putInt("k",kel)
        super.onSaveInstanceState(outState)
    }
}
