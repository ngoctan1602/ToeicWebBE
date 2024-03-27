package com.tantan.ToeicWeb.services.part;

import com.tantan.ToeicWeb.mapper.PartMapper;
import com.tantan.ToeicWeb.repository.PartRepository;
import com.tantan.ToeicWeb.response.PartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartServices implements IPartServices{
    @Autowired
    private PartRepository partRepository;
    @Override
    public List<PartResponse> getAllPart() {
        return partRepository.findAll().stream().map(
                PartMapper.INSTANCE::toDTO
        ).collect(Collectors.toList());
    }
}
