# Myads Library

Helps in loading Facebook Audience Network and Admob Ad ids from APIs

## Installation

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.AhmadSaeed68:myads:1.0'
		implementation 'com.android.volley:volley:1.1.1'
	}



## Usage

```java


public class MainActivity extends AppCompatActivity {

    // Add String Arrays
    public static  String[] admobBannerIds;
    public static  String[] admobInterstitialIds;
    public static  String[] fanBannerIds;
    public static  String[] fanInterstitialIds;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initilizate MyAds with Context and Package Name
        MyAds.Initialize(this, getPackageName());
        //Optional: set API endpoint
        MyAds.setUrl("https://test.ahmadsaeed.net/app/api/adslib?package");
        // Getting Ids and Storing them in global String arrays
        MyAds.getAdIds(
                  response -> {
                      admobBannerIds = MyAds.ParseAdmobBannerIds(response);
                      admobInterstitialIds = MyAds.ParseAdmobInterstitialIds(response);
                      fanBannerIds = MyAds.parseFanBannerIds(response);
                      fanInterstitialIds = MyAds.parseFanInterstitialIds(response);
                  },
                  MyAds::identifyError
        );



```


## License
[Ahmad Saeed](https://ahmadsaeed.net)
