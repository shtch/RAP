package platform;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public final class MainUiLayot {

	private static final int CENTER_AREA_WIDTH = 1000;
	
	public static FormData createHeaderFormData(int headerHeight) {
		FormData data = new FormData();
		data.top = new FormAttachment( 0 );
		data.left = new FormAttachment( 0 );
		data.right = new FormAttachment( 100 );
		data.height = headerHeight+10;
		return data;
	}
	public static FormData createLogoFormData(int headerHeight) {
		FormData data = new FormData();
		data.left = new FormAttachment( 3 );
		data.top = new FormAttachment( 50, -( headerHeight / 2 ) );
		return data;
	}

	public static FormData createTitleFormData(Control logo) {
		FormData data = new FormData();
		data.left = new FormAttachment( logo, 15 );
		data.top = new FormAttachment(0, 5);
		return data;
	}
	public static FormData createUserMenuFormData() {
		FormData data = new FormData();
		data.right = new FormAttachment( 100, -5 );
		data.bottom = new FormAttachment(100, -5);
		return data;
	}
	public static FormData createMainMenuFormData(Control pcont) {
		FormData data = new FormData();
		data.right = new FormAttachment( pcont, -5 );
		data.bottom = new FormAttachment(100, -5);
		return data;
	}

	public static FormData createFooterFormData() {
		FormData data = new FormData();
		data.left = new FormAttachment( 50, ( -1000 / 2 ) );
		data.top = new FormAttachment( 100, -40 );
		data.bottom = new FormAttachment( 100 );
		data.right = new FormAttachment( 100, -10 );
		//			    data.width = CENTER_AREA_WIDTH;// -20 - 2;
		return data;
	}

	public static FormData createFooterLabelFormData( Composite footer ) {
		FormData data = new FormData();
		data.top = new FormAttachment( 50, -10 );
		data.right = new FormAttachment( 100, -10 );
		return data;
	}

	public static FormData createContentBodyFormData( Composite header ) {
		FormData data = new FormData();
		data.top = new FormAttachment( header, 0 );
		data.left = new FormAttachment( 0, 0 );
		data.right = new FormAttachment( 100, 0 );
		data.bottom = new FormAttachment( 100, 0 );
		return data;
	}

	public static FormData createCenterAreaFormData( Composite footer, Composite centerArea ) {
		FormData data = new FormData();
		data.top = new FormAttachment( centerArea, 50, SWT.BOTTOM );
		data.bottom = new FormAttachment( footer, 0, SWT.TOP );
		data.left = new FormAttachment( 50, ( -CENTER_AREA_WIDTH / 2 ) -10  );
		data.width = CENTER_AREA_WIDTH + 10;
		return data;
	}

}
