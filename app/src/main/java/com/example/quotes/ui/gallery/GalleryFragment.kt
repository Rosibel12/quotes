package com.example.quotes.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.quotes.databinding.FragmentGalleryBinding
import com.example.quotes.domain.model.QuoteModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val galleryViewModel: GalleryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentGalleryBinding.inflate(inflater, container, false)

        val root: View = binding.root

        binding.btnCreateQuote.setOnClickListener {
            val id = (1000..99999).random()
            val quoteModel = QuoteModel(id = id, quote = binding.etQuote.text.toString(),
            author = binding.etAuthor.text.toString())
            lifecycleScope.launch(Dispatchers.IO){
                galleryViewModel.addQuote(quoteModel)
            }
            Toast.makeText(activity,"Guardado ...", Toast.LENGTH_LONG).show()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}