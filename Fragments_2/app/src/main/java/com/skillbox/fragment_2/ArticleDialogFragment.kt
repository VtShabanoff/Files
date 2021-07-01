package com.skillbox.fragment_2

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment

class ArticleDialogFragment : DialogFragment() {

    private val itemMultiChoiceListener: ItemMultiChoiceListener?
        get() = activity?.let { it as ItemMultiChoiceListener }

    private val articleNames = arrayOf(
        ArticleSection.CAT,
        ArticleSection.DOG,
        ArticleSection.BIRD
    )
    private val selectedItems = ArrayList<Int>()
    private var arrayCheckItems = BooleanArray(3) { false }
    private lateinit var currentTypes: ArrayList<ArticleSection>
    private var names = Array(3) { "" }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if (arguments != null) {
            currentTypes =
                requireArguments().getParcelableArrayList<ArticleSection>(KEY_CURRENT_TYPE) as ArrayList<ArticleSection>
            arrayCheckItems = createChecks(currentTypes)
            saveItems(currentTypes)
        }

        typeNames()

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("choice group")
            .setMultiChoiceItems(names, arrayCheckItems)
            { _, which, isChecked ->
                if (isChecked) {
                    selectedItems.add(which)
                } else if (selectedItems.contains(which)) {
                    selectedItems.remove(which)
                }
            }
            .setPositiveButton("OK") { _, _ ->
                generateTypes(selectedItems)
                getTypes(currentTypes)

            }
            .setNegativeButton("CANCEL") { dialog, _ ->
                dialog.cancel()
            }
        return builder.create()
    }

    private fun createChecks(currentTypes: ArrayList<ArticleSection>): BooleanArray {
        currentTypes.forEach {
            val indexCurrentType = articleNames.indexOf(it)
            arrayCheckItems[indexCurrentType] = true
            selectedItems.add(indexCurrentType)

        }
        return arrayCheckItems
    }

    private fun saveItems(currentTypes: ArrayList<ArticleSection>){
        currentTypes.forEach {
            val indexCurrentType = articleNames.indexOf(it)
            selectedItems.add(indexCurrentType)
        }
    }

    private fun typeNames() {
        articleNames.forEachIndexed { index, articleSection -> names[index] = articleSection.name }
    }

    private fun generateTypes(selectedItems: ArrayList<Int>) {
        currentTypes = ArrayList() // инициализтруем тут
        selectedItems.forEach { currentTypes.add(articleNames[it]) } // приводим Int к типу enum
    }

    private fun getTypes(currentTypes: ArrayList<ArticleSection>) {
        itemMultiChoiceListener?.onItemMultiChoice(currentTypes)
    }

    companion object {

        private const val KEY_CURRENT_TYPE = "key_selected_list"

        fun newInstance(
            articleSection: ArrayList<ArticleSection>,
        ): ArticleDialogFragment {
            return ArticleDialogFragment().withArguments {
                putParcelableArrayList(KEY_CURRENT_TYPE, articleSection)
            }
        }
    }

}