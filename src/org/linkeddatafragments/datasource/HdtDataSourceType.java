package org.linkeddatafragments.datasource;

import java.io.File;
import java.io.IOException;

import org.linkeddatafragments.exceptions.DataSourceException;

import com.google.gson.JsonObject;

/**
 * The type of HDT-backed Triple Pattern Fragment data sources.
 *
 * @author <a href="http://olafhartig.de">Olaf Hartig</a>
 */
public class HdtDataSourceType implements IDataSourceType
{
    public static final String TYPE_NAME = "HdtDatasource";

    public static void register() {
        if ( ! DataSourceTypesRegistry.isRegistered(TYPE_NAME) ) {
            DataSourceTypesRegistry.register( TYPE_NAME,
                                              new HdtDataSourceType() );
        }
    }

    @Override
    public IDataSource createDataSource( final String title,
                                         final String description,
                                         final JsonObject settings )
                                                     throws DataSourceException
    {
        final String fname = settings.getAsJsonPrimitive("file").getAsString();
        final File file = new File( fname );
        
        try {
            return new HdtDataSource(title, description, file.getAbsolutePath());
        } catch (IOException ex) {
            throw new DataSourceException(ex);
        }
    }

}