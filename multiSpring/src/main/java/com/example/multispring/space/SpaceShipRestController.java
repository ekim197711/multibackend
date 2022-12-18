package com.example.multispring.space;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/space")
@RequiredArgsConstructor
public class SpaceShipRestController {
    private final SpaceShipRepository spaceShipRepository;
    @GetMapping("/")
    public List<SpaceShipDto> ships(){
        List<SpaceShipEntity> allEntities = spaceShipRepository.all();

        return allEntities.stream().map(
                e ->new SpaceShipDto(e.getId(),e.getModel(),e.getFuelLeft(),e.getCaptain(),e.getDestination()
        )).collect(Collectors.toList());
    }
    @PostMapping("/")
    public SpaceShipDto newShip(@RequestBody SpaceShipDto newShip){
        SpaceShipEntity e = spaceShipRepository.save(
                SpaceShipEntity.builder()
                        .captain(newShip.captain())
                        .model(newShip.model())
                        .destination(newShip.destination())
                        .fuelLeft(newShip.fuelLeft())
                        .build()
        );
        return new SpaceShipDto(e.getId(),e.getModel(),e.getFuelLeft(),e.getCaptain(),e.getDestination());
    }
}
