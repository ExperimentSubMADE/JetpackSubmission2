package com.gdktuts.jetpacksubmission;

import android.app.Application;

import com.gdktuts.jetpacksubmission.repository.ApiRepository;
import com.gdktuts.jetpacksubmission.repository.ContentRepository;
import com.gdktuts.jetpacksubmission.repository.RemoteRepository;

public class Injection {

    public static ContentRepository provideRepository(Application application) {

        RemoteRepository remoteRepository = RemoteRepository.getInstance(new ApiRepository(application));

        return ContentRepository.getInstance(remoteRepository);

    }

}
