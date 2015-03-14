package ${YYAndroidPackageName};
import ${YYAndroidPackageName}.R;
import ${YYAndroidPackageName}.RunnerActivity;
import com.yoyogames.runner.RunnerJNILib;

import android.content.Context;
import net.youmi.android.AdManager;
import net.youmi.android.spot.SpotManager;
import android.app.Activity;
import android.widget.AbsoluteLayout;
import android.view.ViewGroup;
import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import android.view.View;

public class youmisdk
	{
    private static Context _context = RunnerActivity.CurrentActivity ; 

    private AdView adView=null;
    private int BannerXPos;
	private int BannerYPos;

	public void Youmi_ini(String id , String key , double type )
		{
		final String _id = id ;
		final String _key = key ;
		final int _type = (int)type ;  
		RunnerActivity.ViewHandler.post( new Runnable() {
    			public void run() 
    			{ 
				AdManager.getInstance( _context ).setUserDataCollect(true);
				AdManager.getInstance( _context ).init( _id , _key , _type <= 0 );			
				}});
		}
	public void Youmi_spot_animation( double type)
		{
		final double _type = type ; 
		RunnerActivity.ViewHandler.post( new Runnable() {
    			public void run() 
    			{ 
				int _num = (int)_type ;  
				switch( _num ) 
					{
					case 0 : 
						SpotManager.getInstance(_context).setAnimationType(SpotManager.ANIM_NONE);
					break;

					case 1 :
						SpotManager.getInstance(_context).setAnimationType(SpotManager.ANIM_SIMPLE);
					break;

					case 2 :
						SpotManager.getInstance(_context).setAnimationType(SpotManager.ANIM_ADVANCE);
					break;
					}	
				}});
		}
	public void Youmi_spot_show()
		{	
		RunnerActivity.ViewHandler.post( new Runnable() {
    			public void run() 
    			{  			 				
				SpotManager.getInstance(_context).showSpotAds( _context );
				}});		
		}
	public void Youmi_banner_show( )
		{
		Youmi_banner_showAt(0,0);
		}

	public void Youmi_banner_showAt( double _x, double _y )
		{
		final int x = (int)_x ;
		final int y = (int)_y ;

		BannerXPos = x ;
		BannerYPos = y ;
			
		RunnerActivity.ViewHandler.post( new Runnable() 
				{
			public void run() 
				{  	
					// exists
				if( adView != null )
					{
					Youmi_banner_move(x,y);
					}	
				else // null
					{		 							
					AbsoluteLayout layout = (AbsoluteLayout)RunnerActivity.CurrentActivity.findViewById(R.id.ad);
				    ViewGroup vg = (ViewGroup)layout;
				    adView = new AdView(RunnerActivity.CurrentActivity, AdSize.FIT_SCREEN);
				    vg.addView((View)adView);

				    // reset
				    AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 
				    					ViewGroup.LayoutParams.WRAP_CONTENT, x , y );
					adView.setLayoutParams( params);
					adView.setVisibility(View.VISIBLE);
					adView.requestLayout();	
					}							
				}});
		}

	public void Youmi_banner_move( double _x , double _y )
		{
		if( adView != null )
			{
			final int x = (int)_x ;
			final int y = (int)_y ;

			BannerXPos = x ;
			BannerYPos = y ;

			RunnerActivity.ViewHandler.post( new Runnable() 
					{
				public void run() 
					{  			 	
					if( x < 0 || y < 0) 
						{
						adView.setVisibility(View.INVISIBLE);
						}
					else
						{
						AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, x,y );
						adView.setLayoutParams( params);
						adView.requestLayout();
						adView.setVisibility(View.VISIBLE);
						}				
					}});
			}
		}
	public void Youmi_banner_destroy()
		{
		if( adView != null )
			{
			RunnerActivity.ViewHandler.post( new Runnable() {
			public void run() 
		    	{
				AbsoluteLayout layout = (AbsoluteLayout)RunnerActivity.CurrentActivity.findViewById(R.id.ad);
				ViewGroup vg = (ViewGroup)layout;
				if(vg!=null)
					{
					vg.removeView( adView );
					}
				adView.setVisibility(View.GONE);	
		    	adView = null;
		    	}});
			}	
		}
	}