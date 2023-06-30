package com.endava.parkinglot.mapper;

import com.endava.parkinglot.DTO.LevelDto;
import com.endava.parkinglot.DTO.ParkingLotCreationDtoRequest;
import com.endava.parkinglot.DTO.ParkingLotCreationDtoResponse;
import com.endava.parkinglot.enums.LotState;
import com.endava.parkinglot.enums.WorkingDays;
import com.endava.parkinglot.model.ParkingLevelEntity;
import com.endava.parkinglot.model.ParkingLotEntity;
import com.endava.parkinglot.model.WorkingDaysEntity;
import com.endava.parkinglot.util.ModelMapperOptional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingMapper {

    private final ModelMapper modelMapper;

    private final ModelMapperOptional modelMapperOptional;

    public ParkingLotEntity mapRequestDtoToEntity(ParkingLotCreationDtoRequest parkingLotCreationDtoRequest) {
        return modelMapper.map(parkingLotCreationDtoRequest, ParkingLotEntity.class);
    }

    public ParkingLotCreationDtoResponse mapEntityToResponseDto(ParkingLotEntity entity) {
        ParkingLotCreationDtoResponse response = new ParkingLotCreationDtoResponse();
        response.setName(entity.getName());
        response.setAddress(entity.getAddress());

        List<WorkingDays> days = new ArrayList<>();
        for (WorkingDaysEntity workingDaysEntity : entity.getWorking_days()){
            days.add(workingDaysEntity.getNameOfDay());
        }
        response.setWorkingDays(days);

        List<LevelDto> levels = new ArrayList<>();
        for (ParkingLevelEntity levelEntity : entity.getLevels()){
            LevelDto levelDto = new LevelDto();
            levelDto.setFloor(levelEntity.getFloor());
            levelDto.setNumber_of_spaces(levelEntity.getNumber_of_spaces());
            levels.add(levelDto);
        }
        response.setLevels(levels);

        response.setWorkingHours(entity.getBeginWorkingHour().toString() + " - " + entity.getEndWorkingHour().toString());

        response.setClosed(entity.getState() != LotState.OPEN);

        response.setOperatesNonStop(entity.getBeginWorkingHour().toString().contains("00:") && entity.getEndWorkingHour().toString().contains("24:"));

        return response;
    }

    public List<ParkingLotCreationDtoResponse> mapListEntityToListResponseDto(List<ParkingLotEntity> parkingLot) {
        List<ParkingLotCreationDtoResponse> responseList = new ArrayList<>();
        for (ParkingLotEntity entity : parkingLot){
            responseList.add(
                    mapEntityToResponseDto(entity)
            );
        }

        return responseList;
    }

    public List<ParkingLotEntity> mapRequestListDtoToListEntity(List<ParkingLotCreationDtoResponse> parkingLot) {
        return modelMapperOptional.mapList(parkingLot, ParkingLotEntity.class);
    }
}
