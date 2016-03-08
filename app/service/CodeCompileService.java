package service;

import com.fasterxml.jackson.databind.JsonNode;
import models.Code;
import models.CodeCompileResult;
import play.libs.F;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.libs.ws.WSRequestHolder;
import play.libs.ws.WSResponse;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletionStage;

/**
 * Created by nghian on 3/3/16.
 */
public class CodeCompileService {
    public static final int REQUEST_TIMEOUT = 10000;

    public CodeCompileService(Code code, String url, WSClient ws) {
        this.code = code;
        this.url = url;
        this.ws = ws;
    }

    private Code code;
    private String url;
    private WSClient ws;

    public CodeCompileResult compile() {
        JsonNode codeJson = Json.toJson(code);
        WSRequest request = ws.url(url);
        System.out.println("URL =" + url);
        WSRequest complexRequest = request.setContentType("application/json");
        F.Promise<JsonNode> responsePromise = complexRequest.post(codeJson.toString()).map(response -> {
           //debugging purpose
//           System.out.println("Response raw data: "  + response.asJson().toString());
           return response.asJson();
        });

        JsonNode node = responsePromise.get(REQUEST_TIMEOUT);

        CodeCompileResult result = Json.fromJson(node, CodeCompileResult.class);
        return result;
    }
}
