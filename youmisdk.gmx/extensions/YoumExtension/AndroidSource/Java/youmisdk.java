package ${YYAndroidPackageName};
import ${YYAndroidPackageName}.R;
import ${YYAndroidPackageName}.RunnerActivity;
import com.yoyogames.runner.RunnerJNILib;

import android.content.Context;
import net.youmi.android.AdManager;
import net.youmi.android.spot.SpotManager;

public class youmisdk extends RunnerActivity
	{
    private static Context _context = RunnerActivity.CurrentActivity ; 


	public void Youmi_ini( double type )
		{
		final int _type = (int)type ;  
		RunnerActivity.ViewHandler.post( new Runnable() {
    			public void run() 
    			{ 
				AdManager.getInstance( _context ).setUserDataCollect(true);
				AdManager.getInstance( _context ).init( "45920033ea75978b" , "10b25d48d5deda46" , _type <= 0 );
				SpotManager.getInstance(_context).loadSpotAds();				
				}});		
		}
/*
	public void Youmi_spot_orientation( double type)
		{
		final double _type = type ; 
		RunnerActivity.ViewHandler.post( new Runnable() {
    			public void run() 
    			{ 
				if (_type == 0 )	
						{
						SpotManager.getInstance(_context).setSpotOrientation( SpotManager.ORIENTATION_PORTRAIT);	
						}
				else 
					{
					SpotManager.getInstance(_context).setSpotOrientation( SpotManager.ORIENTATION_LANDSCAPE );
					}	
				}});						
		}
*/
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
	}