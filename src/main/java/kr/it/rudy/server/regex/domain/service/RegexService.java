package kr.it.rudy.server.regex.domain.service;

import kr.it.rudy.server.regex.application.dto.RegexMatch;
import kr.it.rudy.server.regex.application.dto.RegexResponse;
import kr.it.rudy.server.regex.application.service.RegexProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegexService {
    private final RegexProcessor regexProcessor;

    public RegexResponse testRegex(String patternStr, String text, Boolean caseInsensitive, Boolean multiline, Boolean dotAll) {
        int flags = regexProcessor.flagSetting(caseInsensitive, multiline, dotAll);
        List<RegexMatch> matches = regexProcessor.testRegex(patternStr, text, flags);
        return new RegexResponse(
                true,
                matches,
                matches.size()
        );
    }
}
