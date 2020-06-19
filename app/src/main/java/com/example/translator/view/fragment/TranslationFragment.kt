package com.example.translator.view.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.translator.R
import com.example.translator.util.AppPrefs.getDir
import com.example.translator.util.AppPrefs.getDirTo
import com.example.translator.util.AppPrefs.isAutoDetect
import com.example.translator.util.observe
import com.example.translator.util.shortToast
import kotlinx.android.synthetic.main.fragment_translation.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class TranslationFragment : Fragment(), TextWatcher {

    private lateinit var viewModel: TranslationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel { parametersOf() }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_translation, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            error.observe(viewLifecycleOwner) { context?.shortToast(it) }

            languages.observe(viewLifecycleOwner) {
                when (isAutoDetect(requireContext())) {
                    true -> {
                        getString(R.string.autodetect).apply {
                            input_lang_choose.text = this
                            input_lang.text = this
                        }
                    }
                    false -> {
                        it.find { it.id == getDir(requireContext())[0] }?.lang.apply {
                            input_lang_choose.text = this
                            input_lang.text = this
                        }
                    }
                }

               it.find { it.id == getDir(requireContext())[1] }?.lang.apply {
                   result_lang_choose.text = this
                   result_lang.text = this
               }
            }

            translationResult.observe(viewLifecycleOwner) {
                it?.text?.joinToString().apply {
                    result.text = this
                    translation_holder.visibility = if (this?.isBlank() == true) GONE else VISIBLE
                }
            }
        }

        input.addTextChangedListener(this)
    }

    override fun afterTextChanged(editable: Editable?) {
        updateTranslationCard()
    }

    override fun beforeTextChanged(sequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(sequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    private fun updateTranslationCard() {
        val input = input.text.toString().trim()

        with(viewModel) {
            when (isAutoDetect(requireContext())) {
                true -> translate(input, getDirTo(requireContext()))
                false -> getDir(requireContext()).apply { translate(input, "${this[0]}-${this[1]}") }
            }
        }
    }
}