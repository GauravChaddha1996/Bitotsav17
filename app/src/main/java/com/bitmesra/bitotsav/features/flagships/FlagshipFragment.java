package com.bitmesra.bitotsav.features.flagships;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.features.EventDtoType;
import com.bitmesra.bitotsav.features.IdForFragment;
import com.bitmesra.bitotsav.features.details.DetailsActivity;
import com.bitmesra.bitotsav.utils.ItemClickSupport;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FlagshipFragment extends BaseFragment implements FlagshipViewInterface {


    @BindView(R.id.flagship_recycler_view)
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    FlagshipListAdapter adapter;
    FlagshipPresenter presenter;
    int pos;

    public FlagshipFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_flagship, container, false);
        ButterKnife.bind(this, view);
        presenter = new FlagshipPresenter(getActivity(), this);
        setUpFlagshipList();
        return view;
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.FLAGSHIP;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return IdForFragment.HOME;
    }

    private void setUpFlagshipList() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new FlagshipListAdapter(getActivity(), presenter.getFlagshipEvents());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener((recyclerView1, position, v) -> {
            pos = position;
            TextView view = (TextView) v.findViewById(R.id.flagship_name);
            view.animate().alpha(0f).setDuration(200).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
                    Pair<View, String> pair1 = Pair.create(v.findViewById(R.id.background_image), "event_image");
                    Pair<View, String> pair4 = Pair.create(v.findViewById(R.id.subscribedButton), "event_subscribe_button");
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pair1, pair4);
                    intent.putExtra("eventName", adapter.getEventName(position));
                    intent.putExtra("fetchNetwork", true);
                    intent.putExtra("eventDtoType", EventDtoType.TYPE_FLAGSHIP);
                    startActivityForResult(intent, 20, options.toBundle());
                }
            }).start();
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 20) {
            (recyclerView.getChildAt(pos).findViewById(R.id.flagship_name)).setAlpha(0f);
            ((TextView) recyclerView.getChildAt(pos).findViewById(R.id.flagship_name)).setText(
                    presenter.getFlagshipEvents().get(pos).getName()
            );
            (recyclerView.getChildAt(pos).findViewById(R.id.flagship_name)).animate().alpha(1f).setDuration(500).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    adapter.notifyDataSetChanged();
                }
            }).start();
        }
    }
}
