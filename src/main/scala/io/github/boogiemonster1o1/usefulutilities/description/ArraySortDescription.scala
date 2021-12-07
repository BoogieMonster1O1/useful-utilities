package io.github.boogiemonster1o1.usefulutilities.description

import io.github.cottonmc.cotton.gui.client.{BackgroundPainter, LightweightGuiDescription}
import io.github.cottonmc.cotton.gui.widget.{WButton, WGridPanel, WTextField}
import net.minecraft.text.TranslatableText

import java.util

class ArraySortDescription() extends LightweightGuiDescription {
	protected var arrayInput = new WTextField(new TranslatableText("gui.utilities.sort.inputsuggest"))
	protected var arrayOutput = new WTextField(new TranslatableText("gui.utilities.sort.sorted"))

	{
		val root = new WGridPanel
		setRootPanel(root)
		root.setSize(360, 256)
		arrayInput.setMaxLength(100)
		arrayOutput.setMaxLength(100)
		arrayOutput.setEditable(false)
		root.add(arrayInput, 1, 2, 18, 1)
		root.add(arrayOutput, 1, 10, 18, 1)
		val selSortButton = new WButton(new TranslatableText("gui.utilities.selectionsort"))
		root.add(selSortButton, 1, 4, 18, 1)
		selSortButton.setEnabled(true)
		selSortButton.setOnClick(() => this.arraySelectionSort())
		val insSortButton = new WButton(new TranslatableText("gui.utilities.insertionsort"))
		root.add(insSortButton, 1, 6, 18, 1)
		insSortButton.setEnabled(true)
		insSortButton.setOnClick(() => this.arrayInsertionSort())
		val bubSortButton = new WButton(new TranslatableText("gui.utilities.bubblesort"))
		root.add(bubSortButton, 1, 8, 18, 1)
		bubSortButton.setEnabled(true)
		bubSortButton.setOnClick(() => this.arrayBubbleSort())
		val clearButton = new WButton(new TranslatableText("gui.utilities.sort.clear"))
		root.add(clearButton, 1, 12, 18, 1)
		clearButton.setOnClick(() => {
			arrayInput.setText("")
			arrayOutput.setText("")
		})
		root.validate(this)
	}

	override def addPainters(): Unit = {
		getRootPanel.setBackgroundPainter(BackgroundPainter.VANILLA)
	}

	//noinspection DuplicatedCode
	private def arraySelectionSort(): Unit = {
		var textFieldText = arrayInput.getText
		textFieldText = textFieldText.replace("  ", " ")
		val splitTextFieldText = textFieldText.split(" ")
		val integerArray = convertToIntArray(splitTextFieldText)
		for (i <- 0 until integerArray.length - 1) {
			var index = i
			for (j <- i + 1 until integerArray.length) {
				if (integerArray(j) < integerArray(index)) index = j
			}
			val smallerNumber = integerArray(index)
			integerArray(index) = integerArray(i)
			integerArray(i) = smallerNumber
		}
		var arrayOutputString = util.Arrays.toString(integerArray)
		arrayOutputString = arrayOutputString.replace(",", "")
		arrayOutputString = arrayOutputString.replace("[", "")
		arrayOutputString = arrayOutputString.replace("]", "")
		arrayOutput.setText(arrayOutputString)
	}

	//noinspection DuplicatedCode
	private def arrayInsertionSort(): Unit = {
		var textFieldText = arrayInput.getText
		textFieldText = textFieldText.replace("  ", " ")
		val splitTextFieldText = textFieldText.split(" ")
		val integerArray = convertToIntArray(splitTextFieldText)
		val n = integerArray.length
		for (j <- 1 until n) {
			val key = integerArray(j)
			var i = j - 1
			while ( {
				(i > -1) && (integerArray(i) > key)
			}) {
				integerArray(i + 1) = integerArray(i)
				i -= 1
			}
			integerArray(i + 1) = key
		}
		var arrayOutputString = util.Arrays.toString(integerArray)
		arrayOutputString = arrayOutputString.replace(",", "")
		arrayOutputString = arrayOutputString.replace("[", "")
		arrayOutputString = arrayOutputString.replace("]", "")
		arrayOutput.setText(arrayOutputString)
	}

	//noinspection DuplicatedCode
	private def arrayBubbleSort(): Unit = {
		var textFieldText = arrayInput.getText
		textFieldText = textFieldText.replace("  ", " ")
		val splitTextFieldText = textFieldText.split(" ")
		val integerArray = convertToIntArray(splitTextFieldText)
		val n = integerArray.length
		var temp = 0
		for (i <- 0 until n) {
			for (j <- 1 until (n - i)) {
				if (integerArray(j - 1) > integerArray(j)) {
					temp = integerArray(j - 1)
					integerArray(j - 1) = integerArray(j)
					integerArray(j) = temp
				}
			}
		}
		var arrayOutputString = util.Arrays.toString(integerArray)
		arrayOutputString = arrayOutputString.replace(",", "")
		arrayOutputString = arrayOutputString.replace("[", "")
		arrayOutputString = arrayOutputString.replace("]", "")
		arrayOutput.setText(arrayOutputString)
	}

	private def convertToIntArray(array: Array[String]): Array[Int] = {
		try return util.Arrays.stream(array).mapToInt(str => Integer.parseInt(str)).toArray
		catch {
			case e: Exception =>
				e.printStackTrace()
		}
		new Array[Int](0)
	}
}
