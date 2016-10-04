package platform;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.Version;


public class MainUi extends AbstractEntryPoint {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9008572846345545088L;
	private final static String SHELL_RESIZE_NEEDED = "shellResizeNeeded";

	//private Composite centerArea;
	//    private Navigation navigation;
	//private Composite navBar;
	private static int logoHeight = 100;

	@Override
	protected Shell createShell( Display display ) {
		Shell shell = super.createShell( display );
		shell.setData( RWT.CUSTOM_VARIANT, "mainshell" );
		Listener shellResizeListener = new Listener() {
			@Override
			public void handleEvent( Event event ) {
				updateShellSize( shell );
			}
		};
		display.addListener( SWT.Resize, shellResizeListener );
		shell.addListener( SWT.Resize, shellResizeListener );
		return shell;
	}

	private static void updateShellSize( Shell shell ) {
		if( !Boolean.TRUE.equals( shell.getData( SHELL_RESIZE_NEEDED ) ) ) {
			shell.setData( SHELL_RESIZE_NEEDED, Boolean.TRUE );
			Display display = shell.getDisplay();
			display.asyncExec( new Runnable() {
				@Override
				public void run() {
					Rectangle displayBounds = display.getBounds();
					Point size = shell.computeSize( displayBounds.width, SWT.DEFAULT );
					shell.setSize( size.x, Math.max( size.y, displayBounds.height ) );
					shell.setData( SHELL_RESIZE_NEEDED, Boolean.FALSE );
				}
			} );
		}
	}

	@Override
	protected void createContents( Composite parent ) {
		parent.setLayout( new FillLayout() );
		createContent( parent );
		//	    attachHistoryListener();
		//	    selectInitialContribution();
		//	    removeSplash();
	}
	/*
	  private void attachHistoryListener() {
	    BrowserNavigation history = RWT.getClient().getService( BrowserNavigation.class );
	    if( history != null ) {
	      history.addBrowserNavigationListener( new BrowserNavigationListener() {
	        @Override
	        public void navigated( BrowserNavigationEvent event ) {
	          Examples examples = Examples.getInstance();
	          IExampleContribution contribution = examples.getContribution( event.getState() );
	          if( contribution != null ) {
	            selectContribution( contribution );
	          }
	        }
	      } );
	    }
	  }
	 */

	private Composite createContent( Composite parent ) {
		Composite comp = new Composite( parent, SWT.NONE );
		comp.setLayout( new FormLayout() );
		Composite header = createHeader( comp );
		header.setLayoutData( MainUiLayot.createHeaderFormData(logoHeight));
		createContentBody( comp, header );
		return comp;
	}

	private Composite createHeader( Composite parent ) {
		Composite cheader = new Composite( parent, SWT.NONE );
		cheader.setData( RWT.CUSTOM_VARIANT, "header" );
		cheader.setBackgroundMode( SWT.INHERIT_DEFAULT );
		cheader.setLayout( new FormLayout() );
		Control logo = createLogo( cheader );
		createTitle( cheader, logo );
		Control userMenu = createUserMenu(cheader);
		createMenu(cheader, userMenu);
		cheader.setLayoutData( MainUiLayot.createHeaderFormData(logoHeight));
		return cheader;
	}

	private Control createLogo( Composite parent ) {
		Label logoLabel = new Label( parent, SWT.NONE );
		Image rapLogo = MainUi.getImage( parent.getDisplay(), "RAP-logo.png" );
		logoHeight = rapLogo.getBounds().height;
		logoLabel.setImage( rapLogo );
		logoLabel.setLayoutData( MainUiLayot.createLogoFormData(logoHeight) );
		return logoLabel;
	}

	private void createTitle( Composite parent, Control logo ) {
		Label title = new Label( parent, SWT.NONE );
		title.setText( "Платформа Аргонтус" );//TODO !!!!!!!!!!!!!!!!!!!! resource
		title.setLayoutData( MainUiLayot.createTitleFormData(logo) );
		title.setData( RWT.CUSTOM_VARIANT, "title" );
	}



	private Control createUserMenu(Composite parent) {
//		Composite cuserMenu = new Composite( parent, SWT.NONE );
		
		Label title = new Label( parent, SWT.NONE );
		title.setText( "Вход" );//TODO !!!!!!!!!!!!!!!!!!!! resource
		
		title.setLayoutData(MainUiLayot.createUserMenuFormData());
		title.setData( RWT.CUSTOM_VARIANT, "navigation" );
		return title;
	}

	private void createMenu(Composite parent, Control pcont) {
//		Composite cmenu = new Composite( parent, SWT.NONE );
		Label title = new Label( parent, SWT.NONE );
		title.setText( "Файл Изменить Окно ?" );//TODO !!!!!!!!!!!!!!!!!!!! resource		
		title.setLayoutData(MainUiLayot.createMainMenuFormData(pcont));
		title.setData( RWT.CUSTOM_VARIANT, "navigation" );
	}

	private void createContentBody( Composite parent, Composite header ) {
		Composite composite = new Composite( parent, SWT.NONE );
		composite.setData( RWT.CUSTOM_VARIANT, "mainContentArea" );
		composite.setLayout( new FormLayout() );
		composite.setLayoutData( MainUiLayot.createContentBodyFormData( header ) );
		//			    navigation = createNavigation( composite );
		Composite footer = createFooter( composite );
		createCenterArea( composite, footer );
	}

	private Composite createCenterArea( Composite parent, Composite footer ) {
		Composite centerArea = new Composite( parent, SWT.NONE );
		centerArea.setLayout( new FillLayout() );
		centerArea.setLayoutData( MainUiLayot.createCenterAreaFormData( footer, centerArea ) );
		centerArea.setData( RWT.CUSTOM_VARIANT, "centerArea" );
		return centerArea;
	}


	private Composite createFooter( Composite contentComposite ) {
		Composite footer = new Composite( contentComposite, SWT.NONE );
		footer.setBackgroundMode( SWT.INHERIT_DEFAULT );
		footer.setLayout( new FormLayout() );
		footer.setData( RWT.CUSTOM_VARIANT, "footer" );
		footer.setLayoutData( MainUiLayot.createFooterFormData() );
		Label label = new Label( footer, SWT.NONE );
		label.setData( RWT.CUSTOM_VARIANT, "footerLabel" );
		label.setText( "RAP version: " + getRapVersion() );
		label.setLayoutData( MainUiLayot.createFooterLabelFormData( footer ) );
		return footer;
	}


	/*	  
	  protected void createContents1(Composite parent) {
        parent.setLayout(new GridLayout(2, false));
        Button checkbox = new Button(parent, SWT.CHECK);
        checkbox.setText("Hello");
        Button button = new Button(parent, SWT.PUSH);
        button.setText("World");
    }
	 */	  
	public static Image getImage( Display display, String path ) {
		ClassLoader classLoader = MainUi.class.getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream( "resources/icons/" + path );
		Image result = null;
		if( inputStream != null ) {
			try {
				result = new Image( display, inputStream );
			} finally {
				try {
					inputStream.close();
				} catch( IOException e ) {
					// ignore
				}
			}
		}
		return result;
	}

	private static String getRapVersion() {
		Version version = FrameworkUtil.getBundle( RWT.class ).getVersion();
		StringBuilder resultBuffer = new StringBuilder( 20 );
		resultBuffer.append( version.getMajor() );
		resultBuffer.append( '.' );
		resultBuffer.append( version.getMinor() );
		resultBuffer.append( '.' );
		resultBuffer.append( version.getMicro() );
		resultBuffer.append( " (Build " );
		resultBuffer.append( version.getQualifier() );
		resultBuffer.append( ')' );
		return resultBuffer.toString();
	}

}
