package boogie.utilmod.screens;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WTextField;
import io.github.cottonmc.cotton.gui.widget.data.Alignment;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public class CalculatorScreen extends LightweightGuiDescription{

    private String fieldtext = new String();
    private boolean operation;
    private String operator;
    private double first;
    private double second;

    public CalculatorScreen(){
        operation = false;

        UtilitiesScreen a = new UtilitiesScreen();
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(160, 200);

        WLabel label = new WLabel(new TranslatableText("gui.utilities.calculator"), 0x000000);

        WTextField input = new WTextField();
        root.add(input,1,3,7,1);
        input.setEditable(false);
        input.setSuggestion(new TranslatableText("gui.utilities.calculator.suggestion"));
        input.setEnabledColor(0xFFFFFF);

        WButton clear = new WButton(new TranslatableText("gui.utilities.calculator.clear"));
        clear.setEnabled(true);
        root.add(clear,1,5,7,1);
        clear.setOnClick(new Runnable() {
            @Override
            public void run() {
                fieldtext = new String();
                input.setText(fieldtext);
            }
        });



        WButton SEVEN = new WButton(new TranslatableText("noom.7"));
        SEVEN.setEnabled(true);
        root.add(SEVEN,1,7,1,1);
        SEVEN.setOnClick(new Runnable() {
            @Override
            public void run() {
                fieldtext += "7";
                input.setText(fieldtext);
            }
        });

        WButton EIGHT = new WButton(new TranslatableText("noom.8"));
        EIGHT.setEnabled(true);
        root.add(EIGHT,3,7,1,1);
        EIGHT.setOnClick(new Runnable() {
            @Override
            public void run() {
                fieldtext += "8";
                input.setText(fieldtext);
            }
        });

        WButton NINE = new WButton(new TranslatableText("noom.9"));
        NINE.setEnabled(true);
        root.add(NINE,5,7,1,1);
        NINE.setOnClick(new Runnable() {
            @Override
            public void run() {
                fieldtext += "9";
                input.setText(fieldtext);
            }
        });

        WButton divide = new WButton(new LiteralText("÷"));
        divide.setEnabled(true);
        root.add(divide,7,7,1,1);
        divide.setOnClick(new Runnable() {
            @Override
            public void run() {
                if(!operation) {
                    operation = true;
                    operator = "/";
                    first = Double.parseDouble(fieldtext);
                    fieldtext = new String();
                }
            }
        });


        WButton FOUR = new WButton(new TranslatableText("noom.4"));
        FOUR.setEnabled(true);
        root.add(FOUR,1,9,1,1);
        FOUR.setOnClick(new Runnable() {
            @Override
            public void run() {
                fieldtext += "4";
                input.setText(fieldtext);
            }
        });

        WButton FIVE = new WButton(new TranslatableText("noom.5"));
        FIVE.setEnabled(true);
        root.add(FIVE,3,9,1,1);
        FIVE.setOnClick(new Runnable() {
            @Override
            public void run() {
                fieldtext += "5";
                input.setText(fieldtext);
            }
        });

        WButton SIX = new WButton(new TranslatableText("noom.6"));
        SIX.setEnabled(true);
        root.add(SIX,5,9,1,1);
        SIX.setOnClick(new Runnable() {
            @Override
            public void run() {
                fieldtext += "6";
                input.setText(fieldtext);
            }
        });

        WButton multiply = new WButton(new LiteralText("×"));
        multiply.setEnabled(true);
        root.add(multiply,7,9,1,1);
        multiply.setOnClick(new Runnable() {
            @Override
            public void run() {
                if(!operation) {
                    operation = true;
                    operator = "*";
                    first = Double.parseDouble(fieldtext);
                    fieldtext = new String();
                }
            }
        });


        WButton ONE = new WButton(new TranslatableText("noom.1"));
        ONE.setEnabled(true);
        root.add(ONE,1,11,1,1);
        ONE.setOnClick(new Runnable() {
            @Override
            public void run() {
                fieldtext += "1";
                input.setText(fieldtext);
            }
        });

        WButton TWO = new WButton(new TranslatableText("noom.2"));
        TWO.setEnabled(true);
        root.add(TWO,3,11,1,1);
        TWO.setOnClick(new Runnable() {
            @Override
            public void run() {
                fieldtext += "2";
                input.setText(fieldtext);
            }
        });

        WButton THREE = new WButton(new TranslatableText("noom.3"));
        THREE.setEnabled(true);
        root.add(THREE,5,11,1,1);
        THREE.setOnClick(new Runnable() {
            @Override
            public void run() {
                fieldtext += "3";
                input.setText(fieldtext);
            }
        });

        WButton subtract = new WButton(new LiteralText("-"));
        subtract.setEnabled(true);
        root.add(subtract,7,11,1,1);
        subtract.setOnClick(new Runnable() {
            @Override
            public void run() {
                if(!operation) {
                    operation = true;
                    operator = "-";
                    first = Double.parseDouble(fieldtext);
                    fieldtext = new String();
                }
            }
        });

        WButton ZERO = new WButton(new TranslatableText("noom.0"));
        ZERO.setEnabled(true);
        root.add(ZERO,1,13,1,1);
        ZERO.setOnClick(new Runnable() {
            @Override
            public void run() {
                fieldtext += "0";
                input.setText(fieldtext);
            }
        });

        WButton point = new WButton(new LiteralText("."));
        point.setEnabled(true);
        root.add(point,3,13,1,1);
        point.setOnClick(new Runnable(){
            @Override
            public void run() {
                if(!fieldtext.contains(".")){
                    if(fieldtext.equals("")){
                        fieldtext += "0.";
                    }
                    else {
                        fieldtext += ".";
                    }
                    input.setText(fieldtext);
                }
            }
        });

        WButton equal = new WButton(new LiteralText("="));
        equal.setEnabled(true);
        root.add(equal,5,13,1,1);
        equal.setOnClick(new Runnable() {
            @Override
            public void run() {
                if(operation){
                    operation = false;
                    second = Double.parseDouble(fieldtext);
                    if(operator.equals("+")) fieldtext = Double.toString(first + second);
                    if(operator.equals("-")) fieldtext = Double.toString(first - second);
                    if(operator.equals("*")) fieldtext = Double.toString(first * second);
                    if(operator.equals("/")) fieldtext = Double.toString(first / second);
                    second = Double.NaN;
                    input.setText(fieldtext);
                }
            }
        });

        WButton add = new WButton(new LiteralText("+"));
        add.setEnabled(true);
        root.add(add,7,13,1,1);
        add.setOnClick(new Runnable() {
            @Override
            public void run() {
                if(!operation) {
                    operation = true;
                    operator = "+";
                    first = Double.parseDouble(fieldtext);
                    fieldtext = new String();
                }
            }
        });

        label.setAlignment(Alignment.CENTER);
        root.add(label, 2, 1, 5, 1);

        root.validate(this);
    }

    @Override
    public void addPainters() {
        getRootPanel().setBackgroundPainter(BackgroundPainter.VANILLA);
    }
}
