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
    private lateinit var selectedItems: ArrayList<Int>
    private lateinit var arrayCheckItems: BooleanArray
    private lateinit var currentTypes: ArrayList<ArticleSection>


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        selectedItems = ArrayList()
        if (arguments != null) {
            currentTypes =
                arguments?.getParcelableArrayList<ArticleSection>(KEY_CURRENT_TYPE) as ArrayList<ArticleSection>
            arrayCheckItems = createChecks(currentTypes)
            saveItems(currentTypes)
        } else {
            arrayCheckItems = BooleanArray(3) { true }
            saveItems(articleNames)
        }

        val generatedNames = enumTypesToString(articleNames)

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("choice group")
            .setMultiChoiceItems(generatedNames, arrayCheckItems)
            { _, which, isChecked ->

                if (!isChecked && selectedItems.contains(which)) {
                    Log.d("TAGG", "selectedItems.size >=1 = ${selectedItems.size}")
                    selectedItems.remove(which)

                } else {
                    selectedItems.add(which)
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

    private fun saveItems(articleNames: Array<ArticleSection>) {
        articleNames.forEachIndexed { index, _ ->
            selectedItems.add(index)
        }
    }

    private fun createChecks(currentTypes: ArrayList<ArticleSection>): BooleanArray {
        arrayCheckItems = BooleanArray(3) { false }
        currentTypes.forEach {
            val indexCurrentType = articleNames.indexOf(it)
            arrayCheckItems[indexCurrentType] = true
            selectedItems.add(indexCurrentType)

        }
        return arrayCheckItems
    }

    private fun saveItems(currentTypes: ArrayList<ArticleSection>) {
        currentTypes.forEach {
            val indexCurrentType = articleNames.indexOf(it)
            if (!selectedItems.contains(indexCurrentType)) {
                selectedItems.add(indexCurrentType)
            }
        }
    }

    private fun enumTypesToString(articleNames: Array<ArticleSection>): Array<String> {
        val names = Array(3) { "" }
        articleNames.forEachIndexed { index, articleSection -> names[index] = articleSection.name }
        return names
    }

    private fun generateTypes(selectedItems: ArrayList<Int>) {
        currentTypes = ArrayList()// инициализтруем тут
        selectedItems.forEach { currentTypes.add(articleNames[it]) } // приводим Int к типу enum
    }

    private fun getTypes(currentTypes: ArrayList<ArticleSection>) {
        itemMultiChoiceListener?.onItemMultiChoice(currentTypes)
    }

    private fun isAllFalseOrTrue(booleanArray: BooleanArray): Boolean {
        var x = 1
        for (i in booleanArray) {
            if (!i) {
                return false
            }
        }
        return true
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