"# myads library " 


public class MainActivity extends AppCompatActivity {

    //ads
    public static  String[] admobBannerIds;
    public static  String[] admobInterstitialIds;
    public static  String[] fanBannerIds;
    public static  String[] fanInterstitialIds;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MyAds.Initialize(this, getPackageName());
        MyAds.setUrl("https://eservicespk.ahmadsaeed.net/app/api/adslib?package");
        MyAds.getAdIds(
                  response -> {
                      admobBannerIds = MyAds.ParseAdmobBannerIds(response);
                      admobInterstitialIds = MyAds.ParseAdmobInterstitialIds(response);
                      fanBannerIds = MyAds.parseFanBannerIds(response);
                      fanInterstitialIds = MyAds.parseFanInterstitialIds(response);
                  },
                  MyAds::identifyError
        );


