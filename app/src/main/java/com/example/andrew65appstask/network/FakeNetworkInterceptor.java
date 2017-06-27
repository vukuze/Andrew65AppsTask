package com.example.andrew65appstask.network;

import com.example.andrew65appstask.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.ResponseBody;

public class FakeNetworkInterceptor implements Interceptor {
    // FAKE RESPONSES.
    private final static String RESPONSE_STRING = "" +
            "{ " +
            "	\"response\" : [ " +
            "		{ " +
            "			\"f_name\" 	: \"иВан\", " +
            "			\"l_name\" 	: \"ИваноВ\", " +
            "			\"birthday\"	: \"1987-03-23\", " +
            "			\"avatr_url\"	: \"http://img2.russia.ru/upimg/author/451/image.jpg\", " +
            "			\"specialty\" : [{ " +
            "				\"specialty_id\" : 101, " +
            "				\"name\"	: \"Менеджер\" " +
            "			}] " +
            "		}, " +
            "		{ " +
            "			\"f_name\" 	: \"Петр\", " +
            "			\"l_name\" 	: \"петроВ\", " +
            "			\"birthday\"	: null, " +
            "			\"avatr_url\" : \"http://img2.russia.ru/upimg/author/489/image.jpg\", " +
            "			\"specialty\" : [{ " +
            "				\"specialty_id\" : 101, " +
            "				\"name\"	: \"Менеджер\" " +
            "			}] " +
            "		}, " +
            "		{ " +
            "			\"f_name\" 	: \"Вася\", " +
            "			\"l_name\" 	: \"Пупкин\", " +
            "			\"birthday\"	: \"1985-11-29\", " +
            "			\"avatr_url\" : \"http://img2.russia.ru/upimg/author/422/image.jpg\", " +
            "			\"specialty\" : [{ " +
            "				\"specialty_id\" : 101, " +
            "				\"name\"	: \"Менеджер\" " +
            "			}, " +
            "			{ " +
            "				\"specialty_id\" : 102, " +
            "				\"name\"	: \"Разработчик\" " +
            "			}] " +
            "		}, " +
            "		{ " +
            "			\"f_name\" 	: \"ЕКАТЕРИНА\", " +
            "			\"l_name\" 	: \"пертрова\", " +
            "			\"birthday\"	: \"1990-01-07\", " +
            "			\"avatr_url\" : \"\", " +
            "			\"specialty\" : [{ " +
            "				\"specialty_id\" : 102, " +
            "				\"name\"	: \"Разработчик\" " +
            "			}] " +
            "		}, " +
            "		{ " +
            "			\"f_name\" 	: \"Николай\", " +
            "			\"l_name\" 	: \"Сидоров\", " +
            "			\"birthday\"	: \"\", " +
            "			\"avatr_url\" : null, " +
            "			\"specialty\" : [{ " +
            "				\"specialty_id\" : 102, " +
            "				\"name\"	: \"Разработчик\" " +
            "			}] " +
            "		}, " +
            "		{ " +
            "			\"f_name\" 	: \"Виктор\", " +
            "			\"l_name\" 	: \"Федотов\", " +
            "			\"birthday\"	: \"23-07-2000\", " +
            "			\"avatr_url\" : \"http://img2.russia.ru/upimg/author/317/image.jpg\", " +
            "			\"specialty\" : [{ " +
            "				\"specialty_id\" : 102, " +
            "				\"name\"	: \"Разработчик\" " +
            "			}] " +
            "		}, " +
            "		{ " +
            "			\"f_name\" 	: \"Артур\", " +
            "			\"l_name\" 	: \"ВАрламов\", " +
            "			\"birthday\"	: \"23-07-2000\", " +
            "			\"avatr_url\" : \"http://img2.russia.ru/upimg/author/316/image.jpg\", " +
            "			\"specialty\" : [{ " +
            "				\"specialty_id\" : 102, " +
            "				\"name\"	: \"Разработчик\" " +
            "			}] " +
            "		}, " +
            "		{ " +
            "			\"f_name\" 	: \"Артур\", " +
            "			\"l_name\" 	: \"ВАрламов\", " +
            "			\"birthday\"	: \"23-07-1982\", " +
            "			\"avatr_url\" : \"http://img2.russia.ru/upimg/author/313/image.jpg\", " +
            "			\"specialty\" : [{ " +
            "				\"specialty_id\" : 102, " +
            "				\"name\"	: \"Разработчик\" " +
            "			}] " +
            "		}, " +
            "		{ " +
            "			\"f_name\" 	: \"Руслан\", " +
            "			\"l_name\" 	: \"Русанов\", " +
            "			\"birthday\"	: \"17-10-1984\", " +
            "			\"avatr_url\" : \"http://img2.russia.ru/upimg/author/313/mage.jpg\", " +
            "			\"specialty\" : [{ " +
            "				\"specialty_id\" : 102, " +
            "				\"name\"	: \"Разработчик\" " +
            "			}] " +
            "		}, " +
            "		{ " +
            "			\"f_name\" 	: \"Владимир\", " +
            "			\"l_name\" 	: \"Миронов\", " +
            "			\"birthday\"	: \"03-08-1972\", " +
            "			\"avatr_url\" : \"http://img2.russia.ru/upimg/author/233/image.jpg\", " +
            "			\"specialty\" : [{ " +
            "				\"specialty_id\" : 102, " +
            "				\"name\"	: \"Разработчик\" " +
            "			}] " +
            "		} " +
            "	] " +
            "} ";

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        okhttp3.Response response = null;
        if (BuildConfig.DEBUG) {
            response = new okhttp3.Response.Builder()
                    .code(200)
                    .message(RESPONSE_STRING)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
                    .body(ResponseBody.create(MediaType.parse("application/json"), RESPONSE_STRING.getBytes()))
                    .addHeader("content-type", "application/json")
                    .build();
        } else {
            response = chain.proceed(chain.request());
        }

        return response;
    }
}

