package boogie.utilmod.screens;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WTextField;
import net.minecraft.text.TranslatableText;

import java.util.Arrays;

public class ArraySortScreen extends LightweightGuiDescription {
    @Override
    public void addPainters() {
        getRootPanel().setBackgroundPainter(BackgroundPainter.VANILLA);
    }

    protected WTextField arrayInput = new WTextField(new TranslatableText("gui.utilities.sort.inputsuggest"));
    protected WTextField arrayOutput = new WTextField(new TranslatableText("gui.utilities.sort.sorted"));

    public ArraySortScreen(){
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(360,256);

        arrayInput.setMaxLength(100);
        arrayOutput.setMaxLength(100);

        arrayOutput.setEditable(false);

        root.add(arrayInput,1,2,18,1);
        root.add(arrayOutput,1,10,18,1);

        WButton selSortButton = new WButton(new TranslatableText("gui.utilities.selectionsort"));
        root.add(selSortButton,1,4,18,1);
        selSortButton.setEnabled(true);
        selSortButton.setOnClick(this::arraySelectionSort);

        WButton insSortButton = new WButton(new TranslatableText("gui.utilities.insertionsort"));
        root.add(insSortButton,1,6,18,1);
        insSortButton.setEnabled(true);
        insSortButton.setOnClick(this::arrayInsertionSort);

        WButton bubSortButton = new WButton(new TranslatableText("gui.utilities.bubblesort"));
        root.add(bubSortButton,1,8,18,1);
        bubSortButton.setEnabled(true);
        bubSortButton.setOnClick(this::arrayBubbleSort);

        WButton clearButton = new WButton(new TranslatableText("gui.utilities.sort.clear"));
        root.add(clearButton,1,12,18,1);
        clearButton.setOnClick(()->{
            arrayInput.setText("");
            arrayOutput.setText("");
        });

        root.validate(this);
    }

    private void arraySelectionSort(){
        String textFieldText = arrayInput.getText();
        textFieldText = textFieldText.replace("  "," ");
        String[] splitTextFieldText = textFieldText.split(" ");
        int[] integerArray = convertToIntArray(splitTextFieldText);
        for (int i = 0; i < integerArray.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < integerArray.length; j++){
                if (integerArray[j] < integerArray[index]){
                    index = j;
                }
            }
            int smallerNumber = integerArray[index];
            integerArray[index] = integerArray[i];
            integerArray[i] = smallerNumber;
        }
        String arrayOutputString = Arrays.toString(integerArray);
        arrayOutputString = arrayOutputString.replace(",","");
        arrayOutputString = arrayOutputString.replace("[","");
        arrayOutputString = arrayOutputString.replace("]","");
        arrayOutput.setText(arrayOutputString);
    }

    private void arrayInsertionSort(){
        String textFieldText = arrayInput.getText();
        textFieldText = textFieldText.replace("  "," ");
        String[] splitTextFieldText = textFieldText.split(" ");
        int[] integerArray = convertToIntArray(splitTextFieldText);
        int n = integerArray.length;
        for (int j = 1; j < n; j++) {
            int key = integerArray[j];
            int i = j-1;
            while ( (i > -1) && ( integerArray [i] > key ) ) {
                integerArray [i+1] = integerArray [i];
                i--;
            }
            integerArray[i+1] = key;
        }
        String arrayOutputString = Arrays.toString(integerArray);
        arrayOutputString = arrayOutputString.replace(",","");
        arrayOutputString = arrayOutputString.replace("[","");
        arrayOutputString = arrayOutputString.replace("]","");
        arrayOutput.setText(arrayOutputString);
    }

    private void arrayBubbleSort(){
        String textFieldText = arrayInput.getText();
        textFieldText = textFieldText.replace("  "," ");
        String[] splitTextFieldText = textFieldText.split(" ");
        int[] integerArray = convertToIntArray(splitTextFieldText);
        int n = integerArray.length;
        int temp = 0;
        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                if(integerArray[j-1] > integerArray[j]){
                    temp = integerArray[j-1];
                    integerArray[j-1] = integerArray[j];
                    integerArray[j] = temp;
                }

            }
        }
        String arrayOutputString = Arrays.toString(integerArray);
        arrayOutputString = arrayOutputString.replace(",","");
        arrayOutputString = arrayOutputString.replace("[","");
        arrayOutputString = arrayOutputString.replace("]","");
        arrayOutput.setText(arrayOutputString);
    }

    private int[] convertToIntArray(String[] array){
        try{
            return Arrays.stream(array).mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new int[0];
    }
}
