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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLData extends FileData
{
    private static DocumentBuilderFactory m_DocumentBuilderFactory;
    private Document m_Document;
    
    protected XMLData(String filepath)
    {
        super (filepath);
    }
    
    @Override
    protected void load()
    {
        if (m_DocumentBuilderFactory == null)
        {
            m_DocumentBuilderFactory = DocumentBuilderFactory.newInstance();
        }
        try
        {
            DocumentBuilder builder = m_DocumentBuilderFactory.newDocumentBuilder();
            m_Document = builder.parse(new java.io.File(m_FilePath));
            m_Document.getDocumentElement().normalize();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public NodeList getElementsByTagName(String tagName)
    {
        return m_Document.getElementsByTagName(tagName);
    }
    
    public NodeList getElementsByTagNameNS(String namespace, String tagName)
    {
        return m_Document.getElementsByTagNameNS(namespace, tagName);
    }
    
    public Element getElementById(String elementId)
    {
        return m_Document.getElementById(elementId);
    }
    
    public String getInputEncoding()
    {
        return m_Document.getInputEncoding();
    }
    
    public String getEncoding()
    {
        return m_Document.getXmlEncoding();
    }
    
    public boolean isStandalone()
    {
        return m_Document.getXmlStandalone();
    }
    
    public String getVersion()
    {
        return m_Document.getXmlVersion();
    }
    
    public boolean isStrictErrorChecking()
    {
        return m_Document.getStrictErrorChecking();
    }
}
