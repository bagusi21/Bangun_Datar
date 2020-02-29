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
import kotlinx.android.synthetic.main.fragment_segitiga.*
import orgd3if0060.bangundatar.databinding.FragmentSegitigaBinding
import kotlin.math.pow
import kotlin.math.sqrt


class SegitigaFragment : Fragment() {
    private lateinit var binding: FragmentSegitigaBinding
    private var luas = 0.0
    private var kel = 0.0
    private var simir = 0.0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_segitiga, container, false)

        if (savedInstanceState != null){
            luas = savedInstanceState.getDouble("l")
            kel  = savedInstanceState.getDouble("k")

            binding.tvIdLuassgt.text = luas.toString()
            binding.tvIdKelsgt.text = kel.toString()
        }

        binding.apply {
            hitung2Btn.setOnClickListener {
                val a = e_alas.text.toString()
                val t = e_tinggi.text.toString()
                if (a.isEmpty() || t.isEmpty()){
                    Toast.makeText(context,"Harus Diisi !!", Toast.LENGTH_SHORT).show()
                }else{
                    luas = (a.toDouble() * t.toDouble()) * 0.5
                    tv_id_luassgt.text = luas.toString()

                    simir = sqrt(a.toDouble().pow(2.0) + t.toDouble().pow(2.0))
                    kel = simir + a.toDouble() + t.toDouble()

                    tv_id_kelsgt.text = kel.toString()
                }
            }

            reset2Btn.setOnClickListener {
                e_alas.text.clear()
                e_tinggi.text.clear()

                tv_id_luassgt.text = null
                tv_id_kelsgt.text = null
            }
            share2Btn.setOnClickListener {
                if (tv_id_luassgt.text.toString().isEmpty() || tv_id_kelsgt.text.toString().isEmpty()){
                    Toast.makeText(context,"Belum Ada Hasil !!", Toast.LENGTH_SHORT).show()
                }else{
                    val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
                        .setText("Luas Segitiga      = $luas\n" +
                                 "Keliling Segitiga  = $kel")
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
        outState.putDouble("l",luas)
        outState.putDouble("k",kel)
        super.onSaveInstanceState(outState)
    }
}
