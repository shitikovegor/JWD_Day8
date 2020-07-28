package com.shitikov.task8.controller.command.impl;

import com.shitikov.task8.controller.command.Command;
import com.shitikov.task8.controller.command.type.CommandResponse;
import com.shitikov.task8.model.entity.Book;
import com.shitikov.task8.model.exception.LibraryServiceException;
import com.shitikov.task8.model.service.LibraryService;
import com.shitikov.task8.model.service.impl.LibraryServiceImpl;
import com.shitikov.task8.model.entity.KeyType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveBookByIdCommand implements Command {

    @Override
    public Map<String, List<Book>> execute(Map<String, String> parameters) {
        LibraryService libraryService = new LibraryServiceImpl();
        Map<String, List<Book>> response = new HashMap<>();

        if (parameters.containsKey(KeyType.ID.getName())) {
            String id = parameters.get("id");
            try {
                if (libraryService.removeById(id)) {
                    response.put(CommandResponse.REMOVE_RESPONSE.getMessage(),
                            libraryService.findAll());
                } else {
                    response.put(CommandResponse.NOT_EXIST_RESPONSE.getMessage(),
                            libraryService.findAll());
                }
            } catch (LibraryServiceException lse) {
                response.put(CommandResponse.BAD_RESPONSE.getMessage()
                        .concat(lse.getMessage()), new ArrayList<>());
            }
        }
        return response;
    }
}
