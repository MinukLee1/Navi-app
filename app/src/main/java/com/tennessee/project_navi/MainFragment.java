package com.tennessee.project_navi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;


public class MainFragment extends Fragment
        {

    private static final String TAG ="HomeFragment";
            Activity activity;
    RelativeLayout mapViewContainer;



    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.main_fragment,container,false);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //메인 fragment 시작시, 로그인 여부에 따른 프라그먼트 전환 및 출력조건절


        // Initialize Firebase Auth : 회원가입이 안된상태일시 JoinActivity
        // -> 이 로그인 유무 조건을 통해서, 타 Activity에서도 onCreate에서 똑같이 설정후 기능을 줄수있다 !!
        //로그인 되어있을시 -> 우측하단버튼 : logout 버튼 / 로그아웃 되어있을시 -> 우측하단버튼 : login버튼
        //   startLoginActivity();


        MapView mapView =  new MapView(getActivity());
        ViewGroup mapViewContainer = (ViewGroup) view.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

//트랙킹모드
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode
                .TrackingModeOnWithoutHeading);
        mapView.setZoomLevel(1, true);


        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(36.8336012, 127.1791657);

        MapPOIItem customMarker = new MapPOIItem();
        customMarker.setItemName("상명대학교");
        customMarker.setTag(1);
        customMarker.setMapPoint(mapPoint);
        customMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker.setCustomImageResourceId(R.drawable.navi_mark);
        customMarker.setCustomImageAutoscale(false);
        customMarker.setCustomImageAnchor(0.5f, 1.0f);
        mapView.addPOIItem(customMarker);





        //로그아웃
       /* btnFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               FirebaseAuth.getInstance().signOut();
               StartMyActivity(LoginActivity.class);
                finish();
           }
        });*/
        return view;

    }

    //프라그먼트용으로 변경한 화면전환 메소드
            // 액티비티 : this / 프라그먼트 : getActivity()
            private void StartMyActivity(Class c){
                Intent intent = new Intent(getActivity(), c);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

            @Override
            public void onAttach(Context context) {
                super.onAttach(context);

                if (context instanceof Activity)
                    activity = (Activity) context;
            }
        }