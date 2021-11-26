package omgimbot.app.lsmapps.features.materi;

import java.util.List;

import omgimbot.app.lsmapps.features.materi.model.Materi;

public interface IMateriView {
    void initViews();

    void goToDashboard();

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onDataReady(List<Materi> result);

    void onNetworkError(String cause);
}
