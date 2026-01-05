package kr.it.rudy.server.regex.application.service;

import kr.it.rudy.server.regex.application.dto.RegexMatch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class RegexProcessor {

    public List<RegexMatch> testRegex(String patternStr, String text, int flags) {
        if (!StringUtils.hasText(patternStr)) {
            throw new IllegalArgumentException("pattern must not be empty or null");
        }
        if (!StringUtils.hasText(text)) {
            throw new IllegalArgumentException("text must not be empty or null");
        }

        Pattern pattern = Pattern.compile(patternStr, flags);
        Matcher matcher = pattern.matcher(text);

        // 모든 매칭 찾기
        List<RegexMatch> matches = new ArrayList<>();
        while (matcher.find()) {
            List<String> groups = new ArrayList<>();
            // 그룹 추출 (0번 그룹은 전체 매칭이므로 1번부터)
            for (int i = 1; i <= matcher.groupCount(); i++) {
                groups.add(matcher.group(i));
            }

            matches.add(new RegexMatch(
                    matcher.group(),
                    matcher.start(),
                    matcher.end(),
                    groups
            ));
        }

        return matches;
    }

    public int flagSetting(Boolean caseInsensitive, Boolean multiline, Boolean dotAll) {
        int flags = 0;

        if (Boolean.TRUE.equals(caseInsensitive)) flags |= Pattern.CASE_INSENSITIVE;
        if (Boolean.TRUE.equals(multiline)) flags |= Pattern.MULTILINE;
        if (Boolean.TRUE.equals(dotAll)) flags |= Pattern.DOTALL;

        return flags;
    }
}
