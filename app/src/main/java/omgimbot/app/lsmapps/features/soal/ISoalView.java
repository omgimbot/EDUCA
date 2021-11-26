package omgimbot.app.lsmapps.features.soal;

import java.util.List;

import omgimbot.app.lsmapps.features.materi.model.Materi;
import omgimbot.app.lsmapps.features.soal.model.Soal;

public interface ISoalView {
    void initViews();

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onDataReady(List<Soal> result);

    void onNetworkError(String cause);

    void onSubmit();
}
