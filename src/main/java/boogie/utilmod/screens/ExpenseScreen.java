package boogie.utilmod.screens;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Alignment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.BiConsumer;

import static java.io.File.separator;
import static net.minecraft.client.MinecraftClient.getInstance;

public class ExpenseScreen extends LightweightGuiDescription {
    private static final String runDirString = getInstance().runDirectory.toString();
    private static final String initValString = runDirString + separator + "init.txt";
    private static final String expensesPath = runDirString + separator + "expenses.txt";
    private static File expenses = new File(expensesPath);
    private static File current = new File(initValString);

    @Override
    public void addPainters() {
        getRootPanel().setBackgroundPainter(BackgroundPainter.VANILLA);
    }

    private static Double currentVal = 0.0;

    private ExpenseScreen() throws IOException {
        

        File init = new File(initValString);
        if(init.exists())getInstance().openScreen(new UtilityScreens(new TranslatableText("gui.utilities.expense"),new ExpenseScreen(true)));
        else {
            FileWriter fw = new FileWriter(init);
            init.createNewFile();
            WGridPanel root = new WGridPanel();
            setRootPanel(root);
            root.setSize(128, 128);
            WTextField initVal = new WTextField();
            initVal.setSuggestion(I18n.translate("gui.utilities.expense.initval"));
            WButton done = new WButton(new TranslatableText("gui.utilities.expense.done"));
            done.setOnClick(() -> {
                try {
                    double val = Double.parseDouble(initVal.getText());
                    fw.write(Double.toString(val));
                    fw.close();
                    getInstance().openScreen(new UtilityScreens(new TranslatableText("gui.utilities.expense"), new ExpenseScreen(true)));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });

            root.add(initVal, 1, 1, 5, 1);
            root.add(done, 1, 3, 5, 1);

            root.validate(this);
        }
    }

    ExpenseScreen(boolean Exists) throws IOException {
        if(!Exists) throw new StackOverflowError();
        WPlainPanel root = new WPlainPanel();
        setRootPanel(root);
        root.setSize(384,256);

        if(!expenses.exists()){
            expenses.createNewFile();
            FileReader frd = new FileReader(current);
            BufferedReader bfd = new BufferedReader(frd);
            String initString = bfd.readLine();
            try{
                if(initString == null) initString = "0.0";
                currentVal = Double.valueOf(initString);
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
            }
            FileWriter fw = new FileWriter(expenses);
            fw.write("*"+initString + " " + I18n.translate("gui.utilities.expense.initval"));
            fw.close();
        }
        List<String> expenseData = Files.readAllLines(Paths.get(expensesPath));
        BiConsumer<String, ExpenseList> expenseBiConsumer = (strinj, destination) -> {
            destination.expenseLabel.setText(new LiteralText(strinj));
        };

        WListPanel<String, ExpenseList> expenseListWListPanel = new WListPanel<>(expenseData, ExpenseList::new,expenseBiConsumer);

        root.add(expenseListWListPanel,3,10,128,243);

        WLabel balanceLabel = new WLabel(I18n.translate("gui.utilities.expense.bal") + currentVal);
        balanceLabel.setAlignment(Alignment.CENTER);
        root.add(balanceLabel,145,25,230,10);

        WButton depositButton = new WButton(new TranslatableText("gui.utilities.expense.deposit"));
        root.add(depositButton,145,65,230,10);


        WButton withdrawButton = new WButton(new TranslatableText("gui.utilities.expense.withdraw"));
        root.add(withdrawButton,145,105,230,10);

        WButton openLog = new WButton(new TranslatableText("gui.utilities.expense.open"));
        root.add(openLog,145,145,230,10);
        openLog.setOnClick(()->{
            try {
                Util.getOperatingSystem().open(expenses);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        WButton resetLog = new WButton(new TranslatableText("gui.utilities.expense.reset"));
        root.add(resetLog,145,185,230,10);
        resetLog.setOnClick(()-> getInstance().openScreen(new UtilityScreens(new TranslatableText("gui.utilities.expense.deleteconfirm"),new ExpenseScreen("RESET"))));

        root.validate(this);
    }

    ExpenseScreen(Type type){
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256,128);
        WTextField valField = new WTextField(new TranslatableText("gui.utilities.expense.amount"));
        WTextField reasonField = new WTextField(new TranslatableText("gui.utilities.expense.reason"));
        WButton btnDone = new WButton();
        if(type == Type.DEPOSIT){
            btnDone.setLabel(new TranslatableText("gui.utilities.expense.makedeposit"));
        }
        else if(type == Type.WITHDRAW);

        root.validate(this);
    }

    private ExpenseScreen(String del){
        WGridPanel root = new WGridPanel();
        root.setSize(164,128);
        setRootPanel(root);

        WTextField deleteField = new WTextField();
        deleteField.setSuggestion(I18n.translate("gui.utilities.expense.deleteplaceholder"));

        WButton doneButton = new WButton(new TranslatableText("gui.utilities.expense.deleteokay"));
        doneButton.setAlignment(Alignment.CENTER);

        root.add(deleteField,1,1,7,1);
        root.add(doneButton,1,3,7,1);

        doneButton.setOnClick(()->{
            String textDel = deleteField.getText();
            if(del.equals(textDel)){
                expenses.delete();
                current.delete();
                getInstance().openScreen(new CottonClientScreen(new UtilitiesScreen()));
            }
            else getInstance().openScreen(new CottonClientScreen(new UtilitiesScreen()));
        });

        root.validate(this);
    }

    private static class ExpenseTypeScreen extends CottonClientScreen{

        public ExpenseTypeScreen(Text title, GuiDescription description) {
            super(title, description);
        }

        @Override
        public void onClose() {
            try {
                getInstance().openScreen(new UtilityScreens(new TranslatableText("gui.utilities.expense"),new ExpenseScreen(true)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ExpenseList extends WPlainPanel{
        private WLabel expenseLabel;

        ExpenseList(){
            expenseLabel = new WLabel("");
            this.add(expenseLabel, 1, 2, 100, 18);
        }
    }

    static class ExpenseMainScreen extends CottonClientScreen{
        ExpenseMainScreen() throws IOException {
            super(new TranslatableText("gui.utilities.expense"), new ExpenseScreen());
        }

        @Override
        public void onClose() {
            getInstance().openScreen(new CottonClientScreen(new UtilitiesScreen()));
        }
    }

    enum Type{
        DEPOSIT,WITHDRAW
    }
}
