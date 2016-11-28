package hu.asgames.service.impl;

import hu.asgames.service.api.CodeGeneratorService;

import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author AMiklo on 2016.11.11.
 */
@Service
public class CodeGeneratorServiceImpl implements CodeGeneratorService {

    private static final Pattern HYPHEN_PATTERN = Pattern.compile("-", Pattern.LITERAL);

    @Override
    public String generateRegistrationCode() {
        return HYPHEN_PATTERN.matcher(UUID.randomUUID().toString()).replaceAll(Matcher.quoteReplacement("")).toUpperCase();
    }
}
