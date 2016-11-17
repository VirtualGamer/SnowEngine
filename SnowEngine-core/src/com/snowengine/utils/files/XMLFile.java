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

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLFile extends File
{
    public XMLFile(String filepath)
    {
        super (filepath, DataType.XML);
    }
    
    public XMLData getXMLData()
    {
        return (XMLData) super.getFileData();
    }
    
    public NodeList getElementsByTagName(String tagName)
    {
        return this.getXMLData().getElementsByTagName(tagName);
    }
    
    public NodeList getElementsByTagNameNS(String namespace, String tagName)
    {
        return this.getXMLData().getElementsByTagNameNS(namespace, tagName);
    }
    
    public Element getElementById(String elementId)
    {
        return this.getXMLData().getElementById(elementId);
    }
    
    public String getInputEncoding()
    {
        return this.getXMLData().getInputEncoding();
    }
    
    public String getEncoding()
    {
        return this.getXMLData().getEncoding();
    }
    
    public boolean isStandalone()
    {
        return this.getXMLData().isStandalone();
    }
    
    public String getVersion()
    {
        return this.getXMLData().getVersion();
    }
    
    public boolean isStrictErrorChecking()
    {
        return this.getXMLData().isStrictErrorChecking();
    }
}
