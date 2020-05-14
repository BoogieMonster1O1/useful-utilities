package boogie.utilmod.screens;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.ClientCottonScreen;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static java.io.File.separator;
import static net.minecraft.client.MinecraftClient.getInstance;
import static java.lang.Math.round;

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

        if(current.exists()) getInstance().openScreen(new UtilityScreens(new TranslatableText("gui.utilities.expense"),new ExpenseScreen(true)));
        else {
            FileWriter fw = new FileWriter(current);
            current.createNewFile();
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

        WListPanel<String, ExpenseList> expenseListWListPanel = new WListPanel<>(expenseData, null, ExpenseList::new, expenseBiConsumer);
        expenseListWListPanel.setListItemHeight(25);

        root.add(expenseListWListPanel,3,20,188,203);

        WLabel balanceLabel = new WLabel(I18n.translate("gui.utilities.expense.bal") + currentVal);
        root.add(balanceLabel,205,25,170,10);

        WButton depositButton = new WButton(new TranslatableText("gui.utilities.expense.deposit"));
        root.add(depositButton,205,65,170,10);
        depositButton.setOnClick(()-> getInstance().openScreen(new ExpenseTypeScreen(new TranslatableText("gui.utilities.expense.deposit"),new ExpenseScreen(Type.DEPOSIT))));

        WButton withdrawButton = new WButton(new TranslatableText("gui.utilities.expense.withdraw"));
        root.add(withdrawButton,205,105,170,10);
        withdrawButton.setOnClick(()-> getInstance().openScreen(new ExpenseTypeScreen(new TranslatableText("gui.utilities.expense.withdraw"),new ExpenseScreen(Type.WITHDRAW))));

        WButton openLog = new WButton(new TranslatableText("gui.utilities.expense.open"));
        root.add(openLog,205,145,170,10);
        openLog.setOnClick(()->{
            try {
                Util.getOperatingSystem().open(expenses);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        WButton resetLog = new WButton(new TranslatableText("gui.utilities.expense.reset"));
        root.add(resetLog,205,185,170,10);
        resetLog.setOnClick(()-> getInstance().openScreen(new UtilityScreens(new TranslatableText("gui.utilities.expense.deleteconfirm"),new ExpenseScreen("RESET"))));

        root.validate(this);
    }

    private ExpenseScreen(String del){
        WGridPanel root = new WGridPanel();
        root.setSize(164,128);
        setRootPanel(root);

        WTextField deleteField = new WTextField();
        deleteField.setSuggestion(I18n.translate("gui.utilities.expense.deleteplaceholder"));

        WButton doneButton = new WButton(new TranslatableText("gui.utilities.expense.deleteokay"));

        root.add(deleteField,1,1,7,1);
        root.add(doneButton,1,3,7,1);

        doneButton.setOnClick(()->{
            String textDel = deleteField.getText();
            if(del.equals(textDel)){
                expenses.delete();
                current.delete();
                getInstance().openScreen(new ClientCottonScreen(new UtilitiesScreen()));
            }
            else getInstance().openScreen(new ClientCottonScreen(new UtilitiesScreen()));
        });

        root.validate(this);
    }

    private static class ExpenseTypeScreen extends ClientCottonScreen{

        ExpenseTypeScreen(Text title, GuiDescription description) {
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

    private ExpenseScreen(Type type){

        WPlainPanel root = new WPlainPanel();
        setRootPanel(root);
        root.setSize(256,128);
        WTextField valField = new WTextField(new TranslatableText("gui.utilities.expense.amount"));
        WTextField reasonField = new WTextField(new TranslatableText("gui.utilities.expense.reason"));
        reasonField.setMaxLength(30);
        WButton btnDone = null;
        if(type == Type.DEPOSIT){
            btnDone = new WButton(new TranslatableText("gui.utilities.expense.makedeposit"));
            btnDone.setOnClick(()->{
                try {
                    Double val = Double.parseDouble(valField.getText());
                    FileWriter fw = new FileWriter(expenses,true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    String fwString = "+" + val + " " + reasonField.getText();
                    bw.newLine();
                    bw.write(fwString);
                    bw.close();
                    currentVal +=val;
                    currentVal*=10000;
                    currentVal = Double.parseDouble(Long.toString(round(currentVal)));
                    currentVal/=10000;
                    current.delete();
                    current.createNewFile();
                    FileWriter fw0 = new FileWriter(current);
                    fw0.write(Double.toString(currentVal));
                    fw0.close();
                    getInstance().openScreen(new UtilityScreens(new ExpenseScreen(true)));
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            });
        }
        else if (type == Type.WITHDRAW){
            btnDone = new WButton(new TranslatableText("gui.utilities.expense.makewithdrawal"));
            btnDone.setOnClick(()->{
                try {
                    Double val = Double.parseDouble(valField.getText());
                    BigDecimal a = new BigDecimal(val);
                    FileWriter fw = new FileWriter(expenses,true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    String fwString = "-" + val + " " + reasonField.getText();
                    bw.newLine();
                    bw.write(fwString);
                    bw.close();
                    currentVal -=val;
                    currentVal*=10000;
                    currentVal = Double.parseDouble(Long.toString(round(currentVal)));
                    currentVal/=10000;
                    current.delete();
                    current.createNewFile();
                    FileWriter fw0 = new FileWriter(current);
                    fw0.write(Double.toString(currentVal));
                    fw0.close();
                    getInstance().openScreen(new UtilityScreens(new ExpenseScreen(true)));
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            });
        }
        assert btnDone != null;

        root.add(valField,10,30,80,1);
        root.add(reasonField,100,30,146,1);
        root.add(btnDone,9,70,238,1);

        root.validate(this);
    }

    private static class ExpenseList extends WPlainPanel{
        private WLabel expenseLabel;

        ExpenseList(){
            expenseLabel = new WLabel("");
            this.add(expenseLabel, 1, 2, 100, 10);
        }
    }

    static class ExpenseMainScreen extends ClientCottonScreen{
        ExpenseMainScreen() throws IOException {
            super(new TranslatableText("gui.utilities.expense"), new ExpenseScreen());
        }

        @Override
        public void onClose() {
            getInstance().openScreen(new ClientCottonScreen(new UtilitiesScreen()));
        }
    }

    enum Type{
        DEPOSIT,WITHDRAW
    }
}
