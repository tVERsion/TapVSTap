package ru.tversion.design;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.*;
// класс Font отвечает за оформление текста
public class Font {

    private BitmapFont font;
    private String FONT_PATH;
    // конструктор принимает входные параметры: имя файла в котором хранится шрифт, размер шрифта в кеглях, цвет, отображение(true - вверх ногами)
    public Font(String fileName, int size, Color color, boolean flip) {
        FONT_PATH = fileName;

        String FONT_CHARS = "";
        for (int i = 32; i < 127; i++) {
            FONT_CHARS += (char) i;
        }
        for (int i = 1024; i < 1104; i++) {
            FONT_CHARS += (char) i;
        }
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_PATH));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();

        parameter.size = size;
        parameter.characters = FONT_CHARS;
        parameter.color = color;

        font = generator.generateFont(parameter);
        if (flip) {
            font.getData().scaleY = -1.0f;
            font.getData().scaleX = -1.0f;
        }
    }



    // метод getFont возвращает с генерированный в конструкторе экземпляр класса BitmapFont
    public BitmapFont getFont() {
        return font;
    }
}
