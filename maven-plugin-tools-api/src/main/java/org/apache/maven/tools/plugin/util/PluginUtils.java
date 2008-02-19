package org.apache.maven.tools.plugin.util;

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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.codehaus.plexus.component.repository.ComponentDependency;
import org.codehaus.plexus.util.DirectoryScanner;
import org.codehaus.plexus.util.FileUtils;
import org.codehaus.plexus.util.StringUtils;
import org.codehaus.plexus.util.xml.XMLWriter;

/**
 * Convenience methods to play with Maven plugins.
 *
 * @author jdcasey
 * @version $Id$
 */
public final class PluginUtils
{
    private PluginUtils()
    {
        // nop
    }

    /**
     * @param basedir
     * @param include
     * @return list of included files with default SCM excluded files
     */
    public static String[] findSources( String basedir, String include )
    {
        return PluginUtils.findSources( basedir, include, null );
    }

    /**
     * @param basedir
     * @param include
     * @param exclude
     * @return list of included files
     */
    public static String[] findSources( String basedir, String include, String exclude )
    {
        DirectoryScanner scanner = new DirectoryScanner();
        scanner.setBasedir( basedir );
        scanner.setIncludes( new String[] { include } );
        if ( !StringUtils.isEmpty( exclude ) )
        {
            scanner.setExcludes( new String[] { exclude, StringUtils.join( FileUtils.getDefaultExcludes(), "," ) } );
        }
        else
        {
            scanner.setExcludes( FileUtils.getDefaultExcludes() );
        }

        scanner.scan();

        return scanner.getIncludedFiles();
    }

    /**
     * @param w not null writer
     * @param pluginDescriptor not null
     */
    public static void writeDependencies( XMLWriter w, PluginDescriptor pluginDescriptor )
    {
        w.startElement( "dependencies" );

        for ( Iterator it = pluginDescriptor.getDependencies().iterator(); it.hasNext(); )
        {
            ComponentDependency dep = (ComponentDependency) it.next();

            w.startElement( "dependency" );

            PluginUtils.element( w, "groupId", dep.getGroupId() );

            PluginUtils.element( w, "artifactId", dep.getArtifactId() );

            PluginUtils.element( w, "type", dep.getType() );

            PluginUtils.element( w, "version", dep.getVersion() );

            w.endElement();
        }

        w.endElement();
    }

    /**
     * @param dependencies not null list of <code>Dependency</code>
     * @return list of component dependencies
     */
    public static List toComponentDependencies( List dependencies )
    {
        List componentDeps = new LinkedList();

        for ( Iterator it = dependencies.iterator(); it.hasNext(); )
        {
            Dependency dependency = (Dependency) it.next();

            ComponentDependency cd = new ComponentDependency();

            cd.setArtifactId( dependency.getArtifactId() );
            cd.setGroupId( dependency.getGroupId() );
            cd.setVersion( dependency.getVersion() );
            cd.setType( dependency.getType() );

            componentDeps.add( cd );
        }

        return componentDeps;
    }

    /**
     * @param w not null writer
     * @param name
     * @param value
     */
    public static void element( XMLWriter w, String name, String value )
    {
        w.startElement( name );

        if ( value == null )
        {
            value = "";
        }

        w.writeText( value );

        w.endElement();
    }
}
