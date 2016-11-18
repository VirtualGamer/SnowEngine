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
package com.snowengine.utils.files;

import com.snowengine.graphics.Texture;
import com.snowengine.maths.Vector2;
import com.snowengine.maths.Vector3;
import com.snowengine.objects.Level;
import com.snowengine.objects.tiles.Tile;
import com.snowengine.objects.tiles.TileBase;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TMXData extends XMLData
{
    private Level m_Level;
    
    protected TMXData(String filepath)
    {
        super (filepath);
    }
    
    @Override
    protected void load()
    {
        super.load();
        m_Level = new Level();
        
        Map<Integer, TileBase> tileMap = this.createTileMap();
        int[] tiles = this.loadMap();
        
        int mapWidth = this.getMapWidth();
        int mapHeight = this.getMapHeight();
        int tileWidth = this.getTileWidth();
        int tileHeight = this.getTileHeight();
        System.out.println(tileWidth + ", " + tileHeight);
    
        for (int y = 0; y < mapHeight; y++)
        {
            for (int x = 0; x < mapWidth; x++)
            {
                int tileId = tiles[x + y * mapWidth];
                Tile tile = (Tile) tileMap.get(tileId);
                Vector3 newPosition = new Vector3(x * tileWidth, y * tileHeight, 0);
                if (tile != null)
                {
                    Tile newTile = tile.copy();
                    newTile.move(newPosition);
                    m_Level.addTile(newTile);
                }
            }
        }
    }
    
    private NodeList getMapList()
    {
        return this.getElementsByTagName("map");
    }
    
    private NodeList getTilesetList()
    {
        return this.getElementsByTagName("tileset");
    }
    
    private Node getMapElement(String name)
    {
        NodeList nl = this.getMapList();
        for (int i = 0; i < nl.getLength(); i++)
        {
            NamedNodeMap nodeMap = nl.item(i).getAttributes();
            for (int j = 0; j < nodeMap.getLength(); j++)
            {
                Node node = nodeMap.item(j);
                if (node.getNodeName().equals(name))
                {
                    return node;
                }
            }
        }
        return null;
    }
    
    private Node getTilesetElement(String name)
    {
        NodeList nl = this.getTilesetList();
        for (int i = 0; i < nl.getLength(); i++)
        {
            NamedNodeMap nodeMap = nl.item(i).getAttributes();
            for (int j = 0; j < nodeMap.getLength(); j++)
            {
                Node node = nodeMap.item(j);
                if (node.getNodeName().equals(name))
                {
                    return node;
                }
            }
        }
        return null;
    }
    
    private int getMapWidth()
    {
        Node node = this.getMapElement("width");
        if (node == null)
        {
            return 0;
        }
        return Integer.parseInt(node.getNodeValue());
    }
    
    private int getMapHeight()
    {
        Node node = this.getMapElement("height");
        if (node == null)
        {
            return 0;
        }
        return Integer.parseInt(node.getNodeValue());
    }
    
    private int getTileWidth()
    {
        Node node = this.getMapElement("tilewidth");
        if (node == null)
        {
            return 0;
        }
        return Integer.parseInt(node.getNodeValue());
    }
    
    private int getTileHeight()
    {
        Node node = this.getMapElement("tileheight");
        if (node == null)
        {
            return 0;
        }
        return Integer.parseInt(node.getNodeValue());
    }
    
    private int getTilesetWidth()
    {
        Node node = this.getTilesetElement("columns");
        if (node == null)
        {
            return 0;
        }
        return Integer.parseInt(node.getNodeValue());
    }
    
    private int getTilesetHeight()
    {
        Node node = this.getTilesetElement("rows");
        if (node == null)
        {
            return 0;
        }
        return Integer.parseInt(node.getNodeValue());
    }
    
    private Map<Integer, TileBase> createTileMap()
    {
        Map<Integer, TileBase> tileMap = new HashMap<>();
        Texture[] tileTextures = this.loadTileset();
        
        int id = 0, width = this.getTilesetWidth(), height = this.getTilesetHeight();
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                tileMap.put(id++, new Tile(tileTextures[x + y * width]));
            }
        }
        
        return tileMap;
    }
    
    private Texture[] loadTileset()
    {
        String filepath = null;
        int columns = 0, rows = 0;
    
        NodeList nodeList = this.getElementsByTagName("tileset");
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            NamedNodeMap map = nodeList.item(i).getAttributes();
            for (int j = 0; j < map.getLength(); j++)
            {
                Node node = map.item(j);
                if (node.getNodeName().equals("name"))
                {
                    filepath = "textures/" + node.getNodeValue() + ".png";
                }
                else if (node.getNodeName().equals("columns"))
                {
                    columns = Integer.parseInt(node.getNodeValue());
                }
                else if (node.getNodeName().equals("rows"))
                {
                    rows = Integer.parseInt(node.getNodeValue());
                }
            }
        }
        
        if (filepath == null || columns <= 0 || rows <= 0)
        {
            throw new RuntimeException("Failed to load the map");
        }
        
        return Texture.splitTextures(filepath, columns, rows);
    }
    
    private int[] loadMap()
    {
        List<Integer> tileMap = new ArrayList<>();
        NodeList nodeList = this.getElementsByTagName("layer");
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            NodeList list = nodeList.item(0).getChildNodes();
            Node mapData = list.item(1);
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < mapData.getChildNodes().getLength(); j++)
            {
                builder.append(mapData.getChildNodes().item(j).getNodeValue());
            }
            
            String[] map = builder.toString().split(",");
            for (String tile : map)
            {
                tileMap.add(Integer.parseInt(tile.trim()) - 1);
            }
        }
        int[] result = null;
        if (!tileMap.isEmpty())
        {
            result = new int[tileMap.size()];
            
            for (int i = 0; i < result.length; i++)
            {
                result[i] = tileMap.get(i);
            }
        }
        return result;
    }
    
    public Level getLevel()
    {
        return m_Level;
    }
}
