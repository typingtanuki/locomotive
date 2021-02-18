package com.github.typingtanuki.locomotive.pages;

import com.github.typingtanuki.locomotive.navigation.NavigationCore;
import com.github.typingtanuki.locomotive.widgets.MenuEntryWidget;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.controlsfx.glyphfont.FontAwesome;

import java.util.ArrayDeque;

import static com.github.typingtanuki.locomotive.utils.ButtonUtils.exitButton;
import static com.github.typingtanuki.locomotive.utils.LayoutUtils.horizontal;
import static com.github.typingtanuki.locomotive.utils.LayoutUtils.vertical;

/**
 * The title page
 */
public class MenuPage extends AbstractInstallerPage {
    public MenuPage() {
        super(new ArrayDeque<>());
    }

    @Override
    protected Node makeContent() {
        return vertical(
                new MenuEntryWidget(FontAwesome.Glyph.ARCHIVE, "menu.ppa", this::showPpas),
                new MenuEntryWidget(FontAwesome.Glyph.LAPTOP, "menu.drivers", this::showDrivers),
                new MenuEntryWidget(FontAwesome.Glyph.PLAY_CIRCLE, "menu.packages", this::showPackages),
                new MenuEntryWidget(FontAwesome.Glyph.GEARS, "menu.tools", this::showTools));
    }

    @Override
    protected Pane makeHeader() {
        return new VBox();
    }

    @Override
    protected Pane makeFooter() {
        return horizontal(exitButton());
    }

    public void showPpas() {
        NavigationCore.changePage(new PpaOverviewPage(getNextPages()));
    }

    public void showDrivers() {
        NavigationCore.changePage(new DriverOverviewPage(getNextPages()));
    }

    public void showPackages() {
        NavigationCore.changePage(new RecommendedPackagePage(getNextPages()));
    }

    public void showTools() {
        NavigationCore.changePage(new SetupToolsPage(getNextPages()));
    }
}
