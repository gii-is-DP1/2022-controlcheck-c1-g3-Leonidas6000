package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecoveryRoomService {
	
	RecoveryRoomRepository recoveryRoomRepository;
	
	@Autowired
    public RecoveryRoomService(RecoveryRoomRepository recoveryRoomRepository) {
        this.recoveryRoomRepository = recoveryRoomRepository;
    }
	
    public List<RecoveryRoom> getAll(){
        return recoveryRoomRepository.findAll();
    }

    public List<RecoveryRoomType> getAllRecoveryRoomTypes(){
        return recoveryRoomRepository.findAllRecoveryRoomTypes();
    }

    public RecoveryRoomType getRecoveryRoomType(String typeName) {
        return recoveryRoomRepository.getRecoveryRoomType(typeName);
    }

    public RecoveryRoom save(RecoveryRoom p) throws DuplicatedRoomNameException {
        RecoveryRoomType type=recoveryRoomRepository.getRecoveryRoomType(p.getName());
    	List<RecoveryRoom> list=recoveryRoomRepository.getRecoveryRoomsByType(type);
    	Boolean error=false;
    	for(RecoveryRoom room:list) {
    		if(room.getName()==p.getName()) {
    			error=true;
    		}
    	}
    	if(error) {
    		throw new DuplicatedRoomNameException();
    	}
    	else {
    		return recoveryRoomRepository.save(p);
    	}     
    }

    
}
