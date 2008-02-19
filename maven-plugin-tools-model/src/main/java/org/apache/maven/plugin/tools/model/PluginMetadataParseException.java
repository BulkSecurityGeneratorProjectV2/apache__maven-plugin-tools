package org.apache.maven.plugin.tools.model;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.File;

/**
 * Exception when plugin metadata parsing occurred.
 *
 * @version $Id$
 */
public class PluginMetadataParseException
    extends Exception
{
    /** serialVersionUID */
    static final long serialVersionUID = 4022348153707995574L;

    private final File metadataFile;

    private final String originalMessage;

    /**
     * @param metadataFile
     * @param message
     * @param cause
     */
    public PluginMetadataParseException( File metadataFile, String message, Throwable cause )
    {
        super( "Error parsing file: " + metadataFile + ". Reason: " + message, cause );

        this.metadataFile = metadataFile;
        this.originalMessage = message;
    }

    /**
     * @param metadataFile
     * @param message
     */
    public PluginMetadataParseException( File metadataFile, String message )
    {
        super( "Error parsing file: " + metadataFile + ". Reason: " + message );

        this.metadataFile = metadataFile;
        this.originalMessage = message;
    }

    /**
     * @return the metadata file
     */
    public File getMetadataFile()
    {
        return metadataFile;
    }

    /**
     * @return the original message
     */
    public String getOriginalMessage()
    {
        return originalMessage;
    }
}
