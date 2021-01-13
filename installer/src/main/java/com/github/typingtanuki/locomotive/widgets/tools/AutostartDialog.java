package com.github.typingtanuki.locomotive.widgets.tools;

import com.github.typingtanuki.locomotive.binary.Binary;
import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.utils.LayoutUtils;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.*;

public class AutostartDialog extends Dialog<AutostartStatus> {
    private final Map<String, CheckBox> checks = new LinkedHashMap<>();
    private final Map<String, Binary> bin = new HashMap<>();

    public AutostartDialog(AutostartStatus before) {
        this.setResizable(true);

        DialogPane dialogPane = getDialogPane();
        LayoutUtils.setSize(dialogPane);

        makeCheckboxes(before.getAvailable(), false);
        makeCheckboxes(before.getEnabled(), true);

        setTitle(I18n.get("autostart.dialog.header"));
        dialogPane.setHeaderText(I18n.get("autostart.dialog.title"));
        dialogPane.getStyleClass().add("text-input-dialog");
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        updateGrid();

        setResultConverter((dialogButton) -> {
            ButtonBar.ButtonData data = dialogButton == null ? null : dialogButton.getButtonData();
            return data == ButtonBar.ButtonData.OK_DONE ? rebuildStatus() : null;
        });
    }

    private void updateGrid() {
        VBox vbox = new VBox();
        for (CheckBox checkBox : checks.values()) {
            vbox.getChildren().add(checkBox);
        }
        getDialogPane().setContent(vbox);
    }

    private void makeCheckboxes(List<Binary> binaries, boolean checked) {
        for (Binary binary : binaries) {
            CheckBox cb = new CheckBox(binary.getTitle());
            cb.setSelected(checked);

            cb.setMaxWidth(Double.MAX_VALUE);
            GridPane.setHgrow(cb, Priority.ALWAYS);
            GridPane.setFillWidth(cb, true);

            checks.put(binary.getBinary(), cb);
            bin.put(binary.getBinary(), binary);
        }
    }

    private AutostartStatus rebuildStatus() {
        AutostartStatus out = new AutostartStatus(Collections.emptyList());

        for (Map.Entry<String, CheckBox> entry : checks.entrySet()) {
            String key = entry.getKey();
            CheckBox checkBox = entry.getValue();
            Binary binary = bin.get(key);

            if (checkBox.isSelected()) {
                out.addEnabled(binary);
            } else {
                out.addDisabled(binary);
            }
        }

        return out;
    }
}
