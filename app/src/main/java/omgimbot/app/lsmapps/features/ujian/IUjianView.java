package omgimbot.app.lsmapps.features.ujian;

import java.util.List;

import omgimbot.app.lsmapps.features.materi.model.Materi;
import omgimbot.app.lsmapps.features.ujian.model.Ujian;

public interface IUjianView {
    void initViews();

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onDataReady(List<Ujian> result);

    void onNetworkError(String cause);
}
