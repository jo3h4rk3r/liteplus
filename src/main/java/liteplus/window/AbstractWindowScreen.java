package liteplus.window;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public abstract class AbstractWindowScreen extends Screen {

    public List<Window> windows = new ArrayList<>();

    public AbstractWindowScreen(Text text_1) {
        super(text_1);
    }

    public void render(MatrixStack matrix, int mouseX, int mouseY, float delta) {
        boolean close = true;
        int noneSelected = -1;
        int selected = -1;
        int count = 0;
        for (Window w : windows) {
            if (!w.closed) {
                close = false;
                if (!w.selected) {
                    onRenderWindow(matrix, count, mouseX, mouseY);
                } else {
                    selected = count;
                }

                if (noneSelected >= -1)
                    noneSelected = count;
            }

            if (w.selected && !w.closed) {
                noneSelected = -2;
            }
            count++;
        }

        if (selected >= 0)
            onRenderWindow(matrix, selected, mouseX, mouseY);
        if (noneSelected >= 0)
            windows.get(noneSelected).selected = true;
        if (close)
            this.close();

        super.render(matrix, mouseX, mouseY, delta);
    }

    public void onRenderWindow(MatrixStack matrix, int window, int mX, int mY) {
        if (!windows.get(window).closed) {
            windows.get(window).render(matrix, mX, mY);
        }
    }

    public void drawButton(MatrixStack matrix, String text, int x1, int y1, int x2, int y2) {
        DrawableHelper.fill(matrix, x1, y1, x2 - 1, y2 - 1, 0xFF000000);
        DrawableHelper.fill(matrix, x1 + 1, y1 + 1, x2, y2, 0xFF000000);
        DrawableHelper.fill(matrix, x1 + 1, y1 + 1, x2 - 1, y2 - 1, 0xFF000000);
        drawCenteredText(matrix, textRenderer, text, x1 + (x2 - x1) / 2, y1 + (y2 - y1) / 2 - 4, -1);
    }

    public void selectWindow(int window) {
        int count = 0;
        for (Window w : windows) {
            if (w.selected) {
                w.inactiveTime = 2;
            }

            w.selected = (count == window);
            count++;
        }
    }

    public boolean mouseClicked(double double_1, double double_2, int int_1) {
        int count = 0;
        int nextSelected = -1;
        for (Window w : windows) {
            if (w.selected) {
                w.onMousePressed((int) double_1, (int) double_2);
            }

            if (w.shouldClose((int) double_1, (int) double_2))
                w.closed = true;

            if (w.inactiveTime <= 0 && double_1 > w.x1 && double_1 < w.x2 && double_2 > w.y1 && double_2 < w.y2 && !w.closed) {
                if (w.selected) {
                    nextSelected = -1;
                    break;
                } else {
                    nextSelected = count;
                }
            }
            count++;
        }

        if (nextSelected >= 0) {
            for (Window w : windows)
                w.selected = false;
            windows.get(nextSelected).selected = true;
        }

        return super.mouseClicked(double_1, double_2, int_1);
    }

    public boolean mouseReleased(double double_1, double double_2, int int_1) {
        for (Window w : windows) {
            w.onMouseReleased((int) double_1, (int) double_2);
        }

        return super.mouseReleased(double_1, double_2, int_1);
    }

}
