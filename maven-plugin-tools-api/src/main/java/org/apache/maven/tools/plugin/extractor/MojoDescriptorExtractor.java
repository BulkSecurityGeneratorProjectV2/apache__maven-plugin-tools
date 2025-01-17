package org.apache.maven.tools.plugin.extractor;

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

import org.apache.maven.plugin.descriptor.InvalidPluginDescriptorException;
import org.apache.maven.plugin.descriptor.MojoDescriptor;
import org.apache.maven.tools.plugin.PluginToolsRequest;

import java.util.List;

/**
 * @author jdcasey
 */
public interface MojoDescriptorExtractor
{
    /**
     * Returns the "name" (id) of the extractor.
     *
     * @since 3.7.0
     */
    String getName();

    /**
     * Returns {@code true} if extractor is deprecated.
     *
     * @since 3.7.0
     */
    boolean isDeprecated();

    /**
     * Returns the {@link GroupKey} of extractor, as {@link org.apache.maven.tools.plugin.scanner.MojoScanner} will
     * execute them grouped, and ordered within groups. Must never return {@code null}.
     *
     * @since 3.7.0
     */
    GroupKey getGroupKey();

    /**
     * Execute the mojo extraction.
     *
     * @param request The {@link PluginToolsRequest} containing information for the extraction process.
     * @return a list of mojo descriptors. These may return HTML values for some fields.
     * @throws ExtractionException if any
     * @throws InvalidPluginDescriptorException if any
     * @since 2.5
     */
    List<MojoDescriptor> execute( PluginToolsRequest request )
        throws ExtractionException, InvalidPluginDescriptorException;

    /**
     * The default implementation returns {@code null}.
     * @return the required java version or {@code null} if unknown
     *
     * @since 3.8.0
     */
    default String getRequiredJavaVersion()
    {
        return null;
    }
}