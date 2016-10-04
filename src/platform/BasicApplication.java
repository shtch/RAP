package platform;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.rap.rwt.application.Application.OperationMode;
import org.eclipse.rap.rwt.client.WebClient;
import org.eclipse.rap.rwt.service.ResourceLoader;


public class BasicApplication implements ApplicationConfiguration {

    public void configure(Application application) {
        Map<String, String> properties = new HashMap<String, String>();
        properties.put(WebClient.PAGE_TITLE, "Платформа Аргонтус");

//        properties.put( WebClient.BODY_HTML, readTextFromResource( "resources/body.html", "UTF-8" ) );
//        properties.put( WebClient.HEAD_HTML, readTextFromResource( "resources/head.html", "UTF-8" ) );
        properties.put( WebClient.FAVICON, "resources/favicon.png" );
        properties.put( WebClient.PAGE_OVERFLOW, "scrollY" );
        application.setOperationMode( OperationMode.SWT_COMPATIBILITY );
        application.addEntryPoint("/main", MainUi.class, properties);///////////
        application.addStyleSheet( RWT.DEFAULT_THEME_ID, "theme/theme.css" );
        application.addResource( "resources/favicon.png", createResourceLoader( "resources/favicon.png" ) );
        application.addResource( "resources/loading.gif", createResourceLoader( "resources/loading.gif" ) );
    }

    private static ResourceLoader createResourceLoader( final String resourceName ) {
        return new ResourceLoader() {
          @Override
          public InputStream getResourceAsStream( String resourceName ) throws IOException {
            return getClass().getClassLoader().getResourceAsStream( resourceName );
          }
        };
      }

}
