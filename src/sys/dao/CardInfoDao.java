package sys.dao;

import java.util.List;
import sys.model.CardInfo;

public interface CardInfoDao {
	
	public List<CardInfo> listCardInfos();
	public Integer addCardInfo(CardInfo e);
	public void modifyCardInfo(CardInfo e);
	public void deleteCardInfo(Integer id);
	public CardInfo findByID(Integer id);
	
}
