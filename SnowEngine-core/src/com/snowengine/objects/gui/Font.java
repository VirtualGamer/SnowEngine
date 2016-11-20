/**
 * Copyright (c) 2016 Mark Rienstra
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.snowengine.objects.gui;

import com.snowengine.graphics.Texture;
import com.snowengine.maths.Vector2;
import com.snowengine.objects.GameObject;
import com.snowengine.objects.sprites.Sprite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Font extends GameObject
{
    private Map<String, Texture> m_FontMap;
    private List<GameObject> m_DrawTexts;
    private Vector2 m_Spacing;
    
    public Font(String filepath)
    {
        super ("Font", 0);
        m_FontMap = new HashMap<>();
        m_Spacing = load(filepath, m_FontMap, 26, 3);
        m_DrawTexts = new ArrayList<>();
    }
    
    @Override
    public void render()
    {
        super.render();
        m_DrawTexts.forEach(GameObject::render);
        m_DrawTexts.clear();
    }
    
    public void drawString(String string, Vector2 position, boolean center)
    {
        int strLength = string.length();
        GameObject gameObject = new GameObject(string, 0);
        Vector2 charPos = new Vector2();
        Vector2 horSpacing = new Vector2(m_Spacing.getX(), 0);
        Vector2 verSpacing = new Vector2(0, m_Spacing.getY());
        Vector2 horOffset = new Vector2();
        if (center)
        {
            horOffset.x =  (strLength * 0.55f) * m_Spacing.getX();
        }
        for (int i = 0; i < strLength; i++)
        {
            String c = String.valueOf(string.charAt(i));
            if (Character.isSpaceChar(c.charAt(0)))
            {
                charPos.add(horSpacing);
                continue;
            }
            if (c.equals("\n"))
            {
                charPos.x = 0;
                charPos.add(verSpacing);
                continue;
            }
            Texture texture = m_FontMap.get(c);
            Sprite sprite = new Sprite(texture);
            sprite.move(charPos.add(horSpacing));
            gameObject.addChild(sprite);
        }
        gameObject.move(position.subtract(horOffset));
        
        m_DrawTexts.add(gameObject);
    }
    
    private static Vector2 load(String filepath, Map<String, Texture> font, int width, int height)
    {
        Texture[] textures = Texture.splitTextures(filepath, width, height);
        Vector2 spacing = new Vector2(textures[0].getWidth(), textures[0].getHeight());
        
        // Alphabet Uppercase
        font.put("A", getTexture(textures, 1, 1, width));
        font.put("B", getTexture(textures, 2, 1, width));
        font.put("C", getTexture(textures, 3, 1, width));
        font.put("D", getTexture(textures, 4, 1, width));
        font.put("E", getTexture(textures, 5, 1, width));
        font.put("F", getTexture(textures, 6, 1, width));
        font.put("G", getTexture(textures, 7, 1, width));
        font.put("H", getTexture(textures, 8, 1, width));
        font.put("I", getTexture(textures, 9, 1, width));
        font.put("J", getTexture(textures,10, 1, width));
        font.put("K", getTexture(textures,11, 1, width));
        font.put("L", getTexture(textures,12, 1, width));
        font.put("M", getTexture(textures,13, 1, width));
        font.put("N", getTexture(textures,14, 1, width));
        font.put("O", getTexture(textures,15, 1, width));
        font.put("P", getTexture(textures,16, 1, width));
        font.put("Q", getTexture(textures,17, 1, width));
        font.put("R", getTexture(textures,18, 1, width));
        font.put("S", getTexture(textures,19, 1, width));
        font.put("T", getTexture(textures,20, 1, width));
        font.put("U", getTexture(textures,21, 1, width));
        font.put("V", getTexture(textures,22, 1, width));
        font.put("W", getTexture(textures,23, 1, width));
        font.put("X", getTexture(textures,24, 1, width));
        font.put("Y", getTexture(textures,25, 1, width));
        font.put("Z", getTexture(textures,26, 1, width));
        // Alphabet Lowercase
        font.put("a", getTexture(textures, 1, 2, width));
        font.put("b", getTexture(textures, 2, 2, width));
        font.put("c", getTexture(textures, 3, 2, width));
        font.put("d", getTexture(textures, 4, 2, width));
        font.put("e", getTexture(textures, 5, 2, width));
        font.put("f", getTexture(textures, 6, 2, width));
        font.put("g", getTexture(textures, 7, 2, width));
        font.put("h", getTexture(textures, 8, 2, width));
        font.put("i", getTexture(textures, 9, 2, width));
        font.put("j", getTexture(textures,10, 2, width));
        font.put("k", getTexture(textures,11, 2, width));
        font.put("l", getTexture(textures,12, 2, width));
        font.put("m", getTexture(textures,13, 2, width));
        font.put("n", getTexture(textures,14, 2, width));
        font.put("o", getTexture(textures,15, 2, width));
        font.put("p", getTexture(textures,16, 2, width));
        font.put("q", getTexture(textures,17, 2, width));
        font.put("r", getTexture(textures,18, 2, width));
        font.put("s", getTexture(textures,19, 2, width));
        font.put("t", getTexture(textures,20, 2, width));
        font.put("u", getTexture(textures,21, 2, width));
        font.put("v", getTexture(textures,22, 2, width));
        font.put("w", getTexture(textures,23, 2, width));
        font.put("x", getTexture(textures,24, 2, width));
        font.put("y", getTexture(textures,25, 2, width));
        font.put("z", getTexture(textures,26, 2, width));
        // Numbers
        font.put("0", getTexture(textures, 1, 3, width));
        font.put("1", getTexture(textures, 2, 3, width));
        font.put("2", getTexture(textures, 3, 3, width));
        font.put("3", getTexture(textures, 4, 3, width));
        font.put("4", getTexture(textures, 5, 3, width));
        font.put("5", getTexture(textures, 6, 3, width));
        font.put("6", getTexture(textures, 7, 3, width));
        font.put("7", getTexture(textures, 8, 3, width));
        font.put("8", getTexture(textures, 9, 3, width));
        font.put("9", getTexture(textures,10, 3, width));
        
        // Special characters
        font.put("`", getTexture(textures,11, 3, width));
        font.put("-", getTexture(textures,12, 3, width));
        font.put("=", getTexture(textures,13, 3, width));
        font.put("~", getTexture(textures,14, 3, width));
        font.put("!", getTexture(textures,15, 3, width));
        font.put("@", getTexture(textures,16, 3, width));
        font.put("#", getTexture(textures,17, 3, width));
        font.put("$", getTexture(textures,18, 3, width));
        font.put("%", getTexture(textures,19, 3, width));
        font.put("^", getTexture(textures,20, 3, width));
        font.put("&", getTexture(textures,21, 3, width));
        font.put("*", getTexture(textures,22, 3, width));
        font.put("(", getTexture(textures,23, 3, width));
        font.put(")", getTexture(textures,24, 3, width));
        font.put("_", getTexture(textures,25, 3, width));
        font.put("+", getTexture(textures,26, 3, width));
        
        return spacing;
    }
    
    private static Texture getTexture(Texture[] textures, int x, int y, int width)
    {
        x -= 1;
        y -= 1;
        return textures[x + y * width];
    }
}
