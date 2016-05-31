package navigateMenu;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akhyanvaidya.secusafe.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/**
 * Created by akhyanvaidya on 24/04/16.
 */
public class CctvFragment extends Fragment {

    View myView;
    RequestQueue cctvReqQueue;
    String cctvUrl="http://10.0.2.2/havehas/Products/product_cctv.php";
    TextView tvCctv;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView= inflater.inflate(R.layout.cctv_layout, container, false);



        cctvReqQueue= Volley.newRequestQueue(this.getActivity());
        final JsonObjectRequest cctvJsonReq= new JsonObjectRequest(Request.Method.POST, cctvUrl,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray cctvArray = response.getJSONArray("product");
                            for(int i=0; i<cctvArray.length(); i++){
                                tvCctv= (TextView) getView().findViewById(R.id.tvCctv);
                                JSONObject cctvObj= cctvArray.getJSONObject(i);


                                    String proName = cctvObj.getString("Pro_Name");
                                    String proImage = cctvObj.getString("Pro_Image");
                                    String proPrice = cctvObj.getString("Pro_Price");


                                    tvCctv.setText("Name: " + proName + "\n" +
                                                   "Link: " + proImage + "\n" +
                                                   "Price: " + proPrice);

                            }
                        }catch(JSONException e){
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.toString());

                    }
                }

        );
        cctvReqQueue.add(cctvJsonReq);

        return myView;

    }
}
