package io.github.boogiemonster1o1.usefulutilities.description

import io.github.cottonmc.cotton.gui.client.{BackgroundPainter, LightweightGuiDescription}
import io.github.cottonmc.cotton.gui.widget.{WButton, WGridPanel, WLabel, WTextField}
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment
import net.fabricmc.api.{EnvType, Environment}
import net.minecraft.text.{LiteralText, TranslatableText}

@Environment(EnvType.CLIENT) class CalculatorDescription() extends LightweightGuiDescription {
	private var fieldtext = ""
	private var operation = false
	private var operator = ""
	private var first = .0
	private var second = .0

	{
		val root = new WGridPanel
		setRootPanel(root)
		root.setSize(160, 200)
		val label = new WLabel(new TranslatableText("gui.utilities.calculator"), 0x000000)
		val input = new WTextField
		root.add(input, 1, 3, 7, 1)
		input.setEditable(false)
		input.setSuggestion(new TranslatableText("gui.utilities.calculator.suggestion"))
		input.setEnabledColor(0xFFFFFF)
		val clear = new WButton(new TranslatableText("gui.utilities.calculator.clear"))
		clear.setEnabled(true)
		root.add(clear, 1, 5, 7, 1)
		clear.setOnClick(() => {
			fieldtext = ""
			input.setText(fieldtext)
		})
		val SEVEN = new WButton(new TranslatableText("noom.7"))
		SEVEN.setEnabled(true)
		root.add(SEVEN, 1, 7, 1, 1)
		SEVEN.setOnClick(() => {
			fieldtext += "7"
			input.setText(fieldtext)
		})
		val EIGHT = new WButton(new TranslatableText("noom.8"))
		EIGHT.setEnabled(true)
		root.add(EIGHT, 3, 7, 1, 1)
		EIGHT.setOnClick(() => {
			fieldtext += "8"
			input.setText(fieldtext)
		})
		val NINE = new WButton(new TranslatableText("noom.9"))
		NINE.setEnabled(true)
		root.add(NINE, 5, 7, 1, 1)
		NINE.setOnClick(() => {
			fieldtext += "9"
			input.setText(fieldtext)
		})
		val divide = new WButton(new LiteralText("÷"))
		divide.setEnabled(true)
		root.add(divide, 7, 7, 1, 1)
		divide.setOnClick(() => {
			if (!operation) {
				operation = true
				operator = "/"
				first = fieldtext.toDouble
				fieldtext = ""
			}
		})
		val FOUR = new WButton(new TranslatableText("noom.4"))
		FOUR.setEnabled(true)
		root.add(FOUR, 1, 9, 1, 1)
		FOUR.setOnClick(() => {
			fieldtext += "4"
			input.setText(fieldtext)
		})
		val FIVE = new WButton(new TranslatableText("noom.5"))
		FIVE.setEnabled(true)
		root.add(FIVE, 3, 9, 1, 1)
		FIVE.setOnClick(() => {
			fieldtext += "5"
			input.setText(fieldtext)
		})
		val SIX = new WButton(new TranslatableText("noom.6"))
		SIX.setEnabled(true)
		root.add(SIX, 5, 9, 1, 1)
		SIX.setOnClick(() => {
			fieldtext += "6"
			input.setText(fieldtext)
		})
		val multiply = new WButton(new LiteralText("×"))
		multiply.setEnabled(true)
		root.add(multiply, 7, 9, 1, 1)
		multiply.setOnClick(() => {
			if (!operation) {
				operation = true
				operator = "*"
				first = fieldtext.toDouble
				fieldtext = ""
			}
		})
		val ONE = new WButton(new TranslatableText("noom.1"))
		ONE.setEnabled(true)
		root.add(ONE, 1, 11, 1, 1)
		ONE.setOnClick(() => {
			fieldtext += "1"
			input.setText(fieldtext)
		})
		val TWO = new WButton(new TranslatableText("noom.2"))
		TWO.setEnabled(true)
		root.add(TWO, 3, 11, 1, 1)
		TWO.setOnClick(() => {
			fieldtext += "2"
			input.setText(fieldtext)
		})
		val THREE = new WButton(new TranslatableText("noom.3"))
		THREE.setEnabled(true)
		root.add(THREE, 5, 11, 1, 1)
		THREE.setOnClick(() => {
			fieldtext += "3"
			input.setText(fieldtext)
		})
		val subtract = new WButton(new LiteralText("-"))
		subtract.setEnabled(true)
		root.add(subtract, 7, 11, 1, 1)
		subtract.setOnClick(() => {
			if (!operation) {
				operation = true
				operator = "-"
				first = fieldtext.toDouble
				fieldtext = ""
			}
		})
		val ZERO = new WButton(new TranslatableText("noom.0"))
		ZERO.setEnabled(true)
		root.add(ZERO, 1, 13, 1, 1)
		ZERO.setOnClick(() => {
			fieldtext += "0"
			input.setText(fieldtext)
		})
		val point = new WButton(new LiteralText("."))
		point.setEnabled(true)
		root.add(point, 3, 13, 1, 1)
		point.setOnClick(() => {
			if (!fieldtext.contains(".")) {
				if (fieldtext == "") fieldtext += "0."
				else fieldtext += "."
				input.setText(fieldtext)
			}
		})
		val equal = new WButton(new LiteralText("="))
		equal.setEnabled(true)
		root.add(equal, 5, 13, 1, 1)
		equal.setOnClick(() => {
			if (operation) {
				operation = false
				second = fieldtext.toDouble
				if (operator == "+") fieldtext = "" + (first + second)
				if (operator == "-") fieldtext = "" + (first - second)
				if (operator == "*") fieldtext = "" + (first * second)
				if (operator == "/") fieldtext = "" + (first / second)
				second = Double.NaN
				input.setText(fieldtext)
			}
		})
		val add = new WButton(new LiteralText("+"))
		add.setEnabled(true)
		root.add(add, 7, 13, 1, 1)
		add.setOnClick(() => {
			if (!operation) {
				operation = true
				operator = "+"
				first = fieldtext.toDouble
				fieldtext = ""
			}
		})
		label.setHorizontalAlignment(HorizontalAlignment.CENTER)
		root.add(label, 2, 1, 5, 1)
		root.validate(this)
	}

	override def addPainters(): Unit = {
		this.getRootPanel.setBackgroundPainter(BackgroundPainter.VANILLA)
	}
}
