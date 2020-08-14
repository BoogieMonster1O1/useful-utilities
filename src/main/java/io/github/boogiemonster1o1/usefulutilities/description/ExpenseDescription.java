package io.github.boogiemonster1o1.usefulutilities.description;

import io.github.boogiemonster1o1.usefulutilities.api.UtilityScreen;
import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WListPanel;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.WTextField;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.BiConsumer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;

public class ExpenseDescription extends LightweightGuiDescription {
    private static final String runDirString = MinecraftClient.getInstance().runDirectory.toString();
    private static final String initValString = runDirString + File.separator + "init.txt";
    private static final String expensesPath = runDirString + File.separator + "expenses.txt";
    private static File expenses = new File(expensesPath);
    private static File current = new File(initValString);

    @Override
    public void addPainters() {
        getRootPanel().setBackgroundPainter(BackgroundPainter.VANILLA);
    }

    private static Double currentVal = 0.0;

    public ExpenseDescription() throws IOException {

        if (current.exists())
            MinecraftClient.getInstance().openScreen(new UtilityScreen(new TranslatableText("gui.utilities.expense"), new ExpenseDescription(true)));
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
                    MinecraftClient.getInstance().openScreen(new UtilityScreen(new TranslatableText("gui.utilities.expense"), new ExpenseDescription(true)));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });

            root.add(initVal, 1, 1, 5, 1);
            root.add(done, 1, 3, 5, 1);

            root.validate(this);
        }
    }

    public ExpenseDescription(boolean Exists) throws IOException {
        if (!Exists) throw new StackOverflowError();
        WPlainPanel root = new WPlainPanel();
        setRootPanel(root);
        root.setSize(384, 256);

        if (!expenses.exists()) {
            expenses.createNewFile();
            FileReader frd = new FileReader(current);
            BufferedReader bfd = new BufferedReader(frd);
            String initString = bfd.readLine();
            try {
                if (initString == null) initString = "0.0";
                currentVal = Double.valueOf(initString);
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
            }
            FileWriter fw = new FileWriter(expenses);
            fw.write("*" + initString + " " + I18n.translate("gui.utilities.expense.initval"));
            fw.close();
        }
        List<String> expenseData = Files.readAllLines(Paths.get(expensesPath));
        BiConsumer<String, ExpensePanel> expenseBiConsumer = (strinj, destination) -> {
            destination.expenseLabel.setText(new LiteralText(strinj));
        };

        WListPanel<String, ExpensePanel> expenseListWListPanel = new WListPanel<>(expenseData, ExpensePanel::new, expenseBiConsumer);
        expenseListWListPanel.setListItemHeight(25);

        root.add(expenseListWListPanel, 3, 20, 188, 203);

        WLabel balanceLabel = new WLabel(I18n.translate("gui.utilities.expense.bal") + currentVal);
        balanceLabel.setHorizontalAlignment(HorizontalAlignment.CENTER);
        root.add(balanceLabel, 205, 25, 170, 10);

        WButton depositButton = new WButton(new TranslatableText("gui.utilities.expense.deposit"));
        root.add(depositButton, 205, 65, 170, 10);
        depositButton.setOnClick(() -> MinecraftClient.getInstance().openScreen(new ExpenseTypeScreen(new TranslatableText("gui.utilities.expense.deposit"), new ExpenseDescription(Type.DEPOSIT))));

        WButton withdrawButton = new WButton(new TranslatableText("gui.utilities.expense.withdraw"));
        root.add(withdrawButton, 205, 105, 170, 10);
        withdrawButton.setOnClick(() -> MinecraftClient.getInstance().openScreen(new ExpenseTypeScreen(new TranslatableText("gui.utilities.expense.withdraw"), new ExpenseDescription(Type.WITHDRAW))));

        WButton openLog = new WButton(new TranslatableText("gui.utilities.expense.open"));
        root.add(openLog, 205, 145, 170, 10);
        openLog.setOnClick(() -> {
            try {
                Util.getOperatingSystem().open(expenses);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        WButton resetLog = new WButton(new TranslatableText("gui.utilities.expense.reset"));
        root.add(resetLog, 205, 185, 170, 10);
        resetLog.setOnClick(() -> MinecraftClient.getInstance().openScreen(new UtilityScreen(new TranslatableText("gui.utilities.expense.deleteconfirm"), new ExpenseDescription("RESET"))));

        root.validate(this);
    }

    private ExpenseDescription(String del) {
        WGridPanel root = new WGridPanel();
        root.setSize(164, 128);
        setRootPanel(root);

        WTextField deleteField = new WTextField();
        deleteField.setSuggestion(I18n.translate("gui.utilities.expense.deleteplaceholder"));

        WButton doneButton = new WButton(new TranslatableText("gui.utilities.expense.deleteokay"));
        doneButton.setAlignment(HorizontalAlignment.CENTER);

        root.add(deleteField, 1, 1, 7, 1);
        root.add(doneButton, 1, 3, 7, 1);

        doneButton.setOnClick(() -> {
            String textDel = deleteField.getText();
            if (del.equals(textDel)) {
                expenses.delete();
                current.delete();
                MinecraftClient.getInstance().openScreen(new CottonClientScreen(new UtilitiesListDescription()));
            } else MinecraftClient.getInstance().openScreen(new CottonClientScreen(new UtilitiesListDescription()));
        });

        root.validate(this);
    }

    private static class ExpenseTypeScreen extends CottonClientScreen {

        ExpenseTypeScreen(Text title, GuiDescription description) {
            super(title, description);
        }

        @Override
        public void onClose() {
            try {
                MinecraftClient.getInstance().openScreen(new UtilityScreen(new TranslatableText("gui.utilities.expense"), new ExpenseDescription(true)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private ExpenseDescription(Type type) {

        WPlainPanel root = new WPlainPanel();
        setRootPanel(root);
        root.setSize(256, 128);
        WTextField valField = new WTextField(new TranslatableText("gui.utilities.expense.amount"));
        WTextField reasonField = new WTextField(new TranslatableText("gui.utilities.expense.reason"));
        reasonField.setMaxLength(30);
        WButton btnDone = new WButton();
        if (type == Type.DEPOSIT) {
            btnDone.setLabel(new TranslatableText("gui.utilities.expense.makedeposit"));
            btnDone.setOnClick(() -> {
                try {
                    Double val = Double.parseDouble(valField.getText());
                    FileWriter fw = new FileWriter(expenses, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    String fwString = "+" + val + " " + reasonField.getText();
                    bw.newLine();
                    bw.write(fwString);
                    bw.close();
                    currentVal += val;
                    currentVal *= 10000;
                    currentVal = Double.parseDouble(Long.toString(Math.round(currentVal)));
                    currentVal /= 10000;
                    current.delete();
                    current.createNewFile();
                    FileWriter fw0 = new FileWriter(current);
                    fw0.write(Double.toString(currentVal));
                    fw0.close();
                    MinecraftClient.getInstance().openScreen(new UtilityScreen(new ExpenseDescription(true)));
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            });
        } else if (type == Type.WITHDRAW) {
            btnDone.setLabel(new TranslatableText("gui.utilities.expense.makewithdrawal"));
            btnDone.setOnClick(() -> {
                try {
                    Double val = Double.parseDouble(valField.getText());
                    BigDecimal a = new BigDecimal(val);
                    FileWriter fw = new FileWriter(expenses, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    String fwString = "-" + val + " " + reasonField.getText();
                    bw.newLine();
                    bw.write(fwString);
                    bw.close();
                    currentVal -= val;
                    currentVal *= 10000;
                    currentVal = Double.parseDouble(Long.toString(Math.round(currentVal)));
                    currentVal /= 10000;
                    current.delete();
                    current.createNewFile();
                    FileWriter fw0 = new FileWriter(current);
                    fw0.write(Double.toString(currentVal));
                    fw0.close();
                    MinecraftClient.getInstance().openScreen(new UtilityScreen(new ExpenseDescription(true)));
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            });
        }
        root.add(valField, 10, 30, 80, 1);
        root.add(reasonField, 100, 30, 146, 1);
        root.add(btnDone, 9, 70, 238, 1);

        root.validate(this);
    }

    private static class ExpensePanel extends WPlainPanel {
        private final WLabel expenseLabel;

        public ExpensePanel() {
            expenseLabel = new WLabel("");
            this.add(expenseLabel, 1, 2, 100, 10);
        }
    }

    enum Type {
        DEPOSIT,
        WITHDRAW
    }
}
